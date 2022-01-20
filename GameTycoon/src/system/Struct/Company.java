package system.Struct;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import system.Item.Item;
import system.Tab.ItemTab;
import system.UI.Updateable;


public class Company implements Updateable, Serializable{
	private static final int MAX_PROJECT_COUNT = 3;
	private String companyName;
	private int money;
	private Vector<Game> product = new Vector(100);
	private Vector<DevGame> project = new Vector(3);
	private int projectCount;
	private int popularity;
	private Rule rule;		//�������ʵ� (��� ������ �ɷ��� �Ǵ���)enable?
	private int time = 0;
	private int devSpeed=0;
	private int bill = 100;
	private int floor = 1;
	private int itemTabTime = -13;
	private int devTabTime = -13;	
	
	
	ArrayList<Item> itemSellList = new ArrayList<Item>();
	ArrayList<Developer> devSellList = new ArrayList<Developer>();
	ArrayList<Item> itemList = new ArrayList<Item>();
	ArrayList<Developer> devList = new ArrayList<Developer>();
	
	public Company(String companyName, Developer defaultDev, int time){
		this.companyName = companyName;
		this.projectCount = 0;		
		this.devList.add(defaultDev);
		this.money = 200;
		this.popularity = 1;		
		this.time = time;
		this.setTime(time);
		setItemSellList();
		setDevSellList();
	}
	
	/*
	 * ����, �ҷ����� 
	 */
	
	public int getFloor() {
		return this.floor;
	}
	public boolean addFloor() {
		if (this.floor >= 11) {
			return false;
		}
		this.floor++;
		return true;	
	}
	
	
	
	public boolean saveFile(String fileName) {
	    FileOutputStream fo = null;
	    
	    try {
	        fo = new FileOutputStream("save/"+fileName+".save");
	        ObjectOutputStream save = new ObjectOutputStream(fo);
	        save.writeObject(this);
	        fo.close();
	        save.close();
	
	    } catch (IOException e) {
	        System.out.println("E:��ü������ �������� ���߽��ϴ�.");
	        return false;
	    }
	    return true;
	}	
	public boolean loadFile(String fileName) throws ClassNotFoundException {
	    FileInputStream fo = null;
	    Company com;
	    try {
	    	fo = new FileInputStream("save/"+fileName);
	        ObjectInputStream load = new ObjectInputStream(fo);
	        com =(Company) load.readObject();
	        companyName= com.companyName;
	    	money= com.money;
	    	product = com.product;
	    	project = com.project;
	    	projectCount = com.projectCount;
	    	popularity = com.popularity;
	    	itemList = com.itemList;
	    	devList = com.devList;
	    	rule = com.rule;		//�������ʵ� (��� ������ �ɷ��� �Ǵ���)enable?
	    	time = com.time;
	    	devSpeed= com.devSpeed;	    	       
	        
	        fo.close();
	        load.close();
	
	    } catch (IOException e) {
	        return false;
	    }
	    
	    return true;
	}
	/*	
	**	�ʵ� getter & setter �Ǵ� �⺻ �޼ҵ�
	*/
	public ArrayList<Developer> getDevSellList(){
		return this.devSellList;
	}
	public ArrayList<Item> getItemSellList() {
		return this.itemSellList;
	}
	
	public void setItemTabTime(int iTime) {
		this.itemTabTime = iTime;
	}
	public int getItemTabTime() {
		return this.itemTabTime;
	}
	public void setDevTabTime(int dTime) {
		this.devTabTime = dTime;
	}
	public int getDevTabTime() {
		return this.devTabTime;
	}
	public String getCompanyName() {
		return companyName;
	}
	public int getMoney() {
		return money;
	}
	public String getMoneyToString() {
		DecimalFormat formatter = new DecimalFormat("###,###");
		return formatter.format(this.money);
	}
	public int getTime() {
		return this.time;
	}
	public void setTime(int time) {
		this.time += time;
		gameUpdater.signal(this);
	}
	
	public void setMoney(int money) {
		this.money = money;
		gameUpdater.signal(this);
	}
	public boolean appendMoney(int money) {
		this.setMoney(this.money+money);
        if (this.money < 0) {
            return false;
        }
        return true;
    }
	public DevGame getProject(int i) {
		try {
			return project.get(i);
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
		
	}
	public DevGame[] getProjects() {
		return project.toArray(new DevGame[0]);
	}
	public int getProjectCount() {
		return this.projectCount;
	}
	public Developer[] devListToArray() {
		return devList.toArray(new Developer[0]);
	}
	
	
	public Developer getDev(int n) {
		return devList.get(n);
	}
	/*
	**���� ���߰���
	*/
	
	
	// ���߽��� �޼ҵ�
	// ���� �ڵ� 
	// 0 : ����	1 : ������	2 : ��������	3 : Ÿ��Ʋ	4 : �ִ밳��	5 : �����Ǵ� �帣 �̼���
	public int startDev(String gameTitle, Subject subject, Genre genre, Developer[] team){
		int cost;
		try {
			cost = subject.getCost() + genre.getCost();
		}catch(NullPointerException e){
			return 5;
		}
		
		if(this.money - cost < 0)
			return 1;
		for(Game game : this.product) 
			if (game.getTitle().equals(gameTitle)) 
				return 3;
		for(DevGame game : this.project) {
			if (game.getTitle().equals(gameTitle)) 
				return 3;
		}
		if(gameTitle.equals(""))
			return 3;
		if(team[0] == null)
			return 2;
		if(projectCount > 2)
			return 4;
		
		this.appendMoney(-cost);
		Game newGame = new Game(gameTitle, subject, genre);
		project.add(new DevGame(newGame, team));
		projectCount++;
		for (Developer dev : team) {
			if(dev == null)
				break;
			dev.setWorkable(false);
		}
		gameUpdater.signal(this);
		return 0;
			

	}
	
	// �������� �޼ҵ�, ���� �����ϴ� ��� ������Ʈ�� ����
	public void progressProject() {
		for(DevGame dg : project) {
			if(dg != null)
				dg.addProgress();
		}
		gameUpdater.signal(this);
	}
	
	// �����̿Ϸ�� ����(DevGame::Progress�� 10000�̻�)�� ����մϴ�.
	public void launchGame(DevGame game) {
		Game newGame = game.getGame();
		newGame.setLaunchTime(this.time);
		newGame.setPrice();
		product.add(newGame);
		project.remove(game);
		projectCount--;
		for(Developer dev : game.getTeam()) {
			if(dev == null)
				break;
			dev.setWorkable(true);
		}
		gameUpdater.signal(this);
	}
	
	//���� �Ǹ�
	public void sellGame() {
		Random rand = new Random();
		for(Game g : product) {
			int howOld = this.time - g.getLaunchTime();
			if(howOld > 90)
				continue;
			double var;
			if((var = 10/howOld -1) < 0)
				var = 0;
			
			int salesValume = (g.getInterest() * this.popularity);
			
			salesValume*= (rand.nextInt(3)/10+1+var); //
			
			System.out.println(salesValume);
			this.appendMoney(g.getPrice() * salesValume);
			g.setCumulativeSales(salesValume);
			
		}
		
	}

	/*
	**�����۰���
	*/
	
	public ArrayList<Item> getItemList() {
		return this.itemList;
	}
	
	public void addItemList(Item nonePlacedItem){
		this.itemList.add(nonePlacedItem);
	}	
	
	public void setItemSellList() {
		this.itemSellList.clear();
		for(int i =0 ; i<3 ; i++) {
			this.itemSellList.add(new Item());
		}
	}
	public void setDevSellList() {
		this.devSellList.clear();
		for(int i =0 ; i<4; i++) {
			this.devSellList.add(new Developer());
		}
	}

	/*
	**û����ó��
	*/
	
	public void setStatus3(int efficiency) {
		this.devSpeed+=efficiency;
	}

	public void addDevList(Developer dev) {
		// TODO Auto-generated method stub
		devList.add(dev);
	}
	public ArrayList<Developer> getDevList(){
		return this.devList;
	}
	public Vector<Developer> getFreeDevList() {
		Vector<Developer> freeDevs = new Vector<Developer>();
		for(int i = 0; i < devList.size() ; i++) {
			if(devList.get(i).isWorkable()) {
				freeDevs.add(devList.get(i));
			}
		}
		return freeDevs;
		
	}
	//���� ��
	public boolean adjustment() {	// ������� �������޸� �����ϵ���
		int costOfMaintance = 0;
		int totalSalary = 0;
		for(Item item : this.itemList) {
			costOfMaintance += item.getStatus2();
		}
		for(Developer dev : this.devList) {
			totalSalary += dev.getSalary();
		}
		return this.appendMoney(-(costOfMaintance+totalSalary+bill));
		
		
	}

	public Object getProduct() {
		// TODO Auto-generated method stub
		return null;
	}



	
}
