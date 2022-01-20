package system.Item;

import java.io.Serializable;
import java.util.Random;

import javax.swing.JLabel;

import system.Struct.ItemType;
import system.UI.GameUI;
import system.UI.Updateable;

public class Item implements GameUI, Updateable, Serializable {
	private String name;
	private String imgAdress;
	private String tabImgAdress;
	private int isPlaced = 0;//배치관리 변수 0이면 배치전, 1이면 배치대기(클릭시 배치), 2이면 배치완료
	private int income = 0;
	private int recharge = 0;
	private int efficiency = 0;
	public int xPos=-1;
	public int yPos=-1;
	private boolean selectToggle = true;
	private int cost = 2;
	private String ItemList[] = {"화분","소파","냉장고","에어컨","자판기","게임기",
			"당구대","안마의자","서버 컴퓨터"};		
	
	public Item(String name) {
		this.name = name;
		//this.img = new JLabel(name);
	}
	public Item() {
		randItem();
		ItemStat();
	}
	
	public void randItem() {
		Random random = new Random();
		int rand = random.nextInt(30);
		if(rand>=0&&rand<7)this.name = ItemList[0];
		else if(rand>=7&&rand<13)this.name = ItemList[1];
		else if(rand>=13&&rand<19)this.name = ItemList[2];
		else if(rand>=19&&rand<24)this.name = ItemList[3];
		else if(rand>=24&&rand<29)this.name = ItemList[4];
		else if(rand>=29&&rand<33)this.name = ItemList[5];
		else if(rand>=33&&rand<37)this.name = ItemList[6];
		else if(rand>=37&&rand<40)this.name = ItemList[7];
		else if(rand>=40&&rand<41)this.name = ItemList[8];	
	}
	
	public void ItemStat() {
		switch(this.name) {	
		case "화분":
			int rand = new Random().nextInt(3);
			if(rand==0) {
				this.cost =9;
				this.recharge =5;
				this.imgAdress = "flower1_64,128";
				this.tabImgAdress = "flower1_64,64";
			}else if(rand == 1) {
				this.cost = 7;
				this.recharge =4;
				this.imgAdress = "flower2_64,128";
				this.tabImgAdress = "flower2_64,64";
			}else if(rand == 2) {
				this.cost =5;
				this.recharge =3;
				this.imgAdress = "flower3_64,128";
				this.tabImgAdress = "flower3_64,64";
			}		
			break;
			//this.img = ();
		case "소파":
			this.cost = 30;
			this.recharge = 10;
			this.imgAdress = "Sofa_64,128";
			this.tabImgAdress = "Sofa_64,64";
			break;
		case "냉장고":
			this.cost = 70;
			this.recharge = 5;
			this.efficiency = 5;
			this.imgAdress = "Refrigerater_64,128";
			this.tabImgAdress = "Refrigerater_64,64";
			break;
		case "에어컨":
			this.cost = 100;
			this.recharge = 10;
			this.efficiency = 10;
			this.income = -5;	
			this.imgAdress = "Aircondithioner_64,128";
			this.tabImgAdress = "Aircondithioner_64,64";
			break;
		case "자판기":
			rand = new Random().nextInt(2);
			if(rand==0) {
				this.cost = 170;
				this.recharge = 10;			
				this.income = 5;
				this.imgAdress = "Cola_64,128";
				this.tabImgAdress = "Cola_64,64";
			}
			else if(rand==1) {
				this.cost = 150; 
				this.recharge = 8;				
				this.income = 5;
				this.imgAdress = "Drink_64,128";
				this.tabImgAdress = "Drink_64,64";
			}
			this.efficiency = 5;
			break;
		case "게임기":
			this.cost = 250;
			this.recharge = 15;
			this.efficiency = -15;
			this.income = 5;
			this.imgAdress = "GameMachine_64,128";
			this.tabImgAdress = "GameMachine_64,64";
			break;
		case "안마의자":
			this.cost = 400;
			this.recharge = 20;
			this.efficiency = -5;
			this.imgAdress = "massageChiar_64,128";
			this.tabImgAdress = "massageChiar_64,64";
			break;
		case "당구대":
			this.cost = 500;
			this.recharge = 15;
			this.efficiency = -10;
			this.income = 10;
			this.imgAdress = "Billiards_64,128";
			this.tabImgAdress = "Billiards_64,64";
			break;		
		}
	}
	public int getStatus1() {
		return this.income; 
	}
	public int getStatus2() {
		return this.recharge;
	}
	public int getStatus3() {
		return this.efficiency;
	}
	
	public String getAdress() {		 
		return this.imgAdress;
	}
	public String getTabAdress() {		 
		return this.tabImgAdress;
	}
	public String getName() {
		return this.name;
	}
	public void purchase(int itemNumber) {		
	}
	public int getIsPlaced() {
		return isPlaced;
	}
	public void setIsPlaced(int cnt) {
		this.isPlaced = cnt;
	}
	public void setSelectToggle(boolean b) {		
		this.selectToggle = b;
	}
	public boolean getSelectToggle() {
		return this.selectToggle;
	}
	public int getCost() {
        return this.cost;
    }
}
