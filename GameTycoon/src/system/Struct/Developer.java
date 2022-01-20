package system.Struct;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JLabel;

import system.UI.GameUI;

public class Developer implements Runnable,GameUI, Serializable{
	private String name;
	private int salary;		
	private int ability;	//개발속도에 영향	10~50
	private int maxHealth;		//30~60
	private int health;	
	
	private boolean workable = false;
	
	
	public Point deskPos = new Point(-1,-1);
		 
	private Thread thread;
	private Image sprite;
	
	public int x = -1;
	public int y = -1;
	
	public int frameNum = 0;
	public int direction = 1; //-1 or i
	public int dst_x = -1;
	public int dst_y = -1;
	
	public boolean isMoving= false;
	
	private boolean selectToggle=false;//직원 구매탭에서 직원 구매를 위한 토글(업그레이드 버전)
	
	private int isPlaced = 0;//직원 배치 변수
	
	
	
	public Developer() { 
		// 게임의 턴, 회사의 인기도, 회사의 복지수준에 따라 좋은 직원이 나오도록	
		// 랜덤이름(성, 이름 조합)	
		this.name = FirstNameSet.getName() + LastNameSet.getName();
		
		Random random = new Random();
		this.ability = 10 + random.nextInt(40);
		this.maxHealth = 15+ random.nextInt(15);
		this.health = maxHealth;
		this.salary = ability*3;
		//this(name, ability, health);	
		this.sprite = Toolkit.getDefaultToolkit().getImage("image/dev_1.png");
		
	}
	
	public Developer(String name, int ability, int maxHealth) {
        this.name = name;
        this.ability = ability;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
		this.salary = ability*3;
		this.sprite = Toolkit.getDefaultToolkit().getImage("image/dev_1.png");
		
	}
	public Image getSprite() {
		return sprite;
	}
	public int getAbility() {
		return this.ability;
	}
	
	//휴식, 환경점수에 영향
	public void rest(int en) {	
		if((health+en) < maxHealth)
			health = maxHealth;
	}
	
	
	public boolean work() {
		System.out.println(this.health);
		if(this.isMoving)
			return false;
		if( --this.health < 0) {
			this.health = 0;
			return false;
		}
		return true;
	}
	
	public boolean isWorkable() {
		return this.workable;
	}
	
	public void setWorkable(boolean b) {
		this.workable = b;
	}
	public String getName() {
		return name;
	}	
	public int getHealth() {
		return this.health;
	}
	public void setSelectToggle(boolean Toggle) {
		this.selectToggle = Toggle;
	}
	
	public boolean getSelectToggle() {
		return this.selectToggle;
	}
	public int getSalary() {
		return this.salary;
	}
	@Override
	public String toString() {
		return this.name + "(" + this.ability +", "+ this.health+"/"+this.maxHealth+")";
	}
	public void setIsPlaced(int isPlaced) {
		this.isPlaced = isPlaced;
	}
	public int getIsPlaced() {
		return this.isPlaced;
	}
	
	
	
	
	public void setDesk(int x, int y) {
		deskPos.setLocation(x*64+140, y*250+100);
		System.out.println(deskPos);
		this.x = deskPos.x;
		this.y = deskPos.y;
		setWorkable(true);
		
	}
	public void goDesk() {
		goTo(deskPos.x, deskPos.y);
	}
	public void goHome() {		
		goTo(800, 2600);
	}
	public void goTo(int dst_x, int dst_y) {
		if(isMoving) 
			return;
		
		this.dst_x =dst_x;
		this.dst_y =dst_y;
		
		//다른측이라면? 엘리베이터쪽으로
		if(this.y != this.dst_y) {	
			this.direction = -1;
		//같은층이라면 목표쪽으로
		}else {	
			if(this.x - this.dst_x > 0) {
				this.direction = -1;
			}else
				this.direction = 1;
		}
		
		this.isMoving = true;
		thread = new Thread(this);
		thread.start();
	}	
	
	
	/*
	 *	스프라이트 동작관련 
	 */
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int frmSpeed = 0;
		while(isMoving) {
			try {
				Thread.sleep(20);			//프레임 속도
				//프레임변경
				if(++frmSpeed == 4) {
					if(++this.frameNum == 4)
						this.frameNum = 0;
					frmSpeed = 0;
				}
				if(dst_x ==-1 && dst_y == -1) {

					System.out.println("뭐야");
				}
				//방향설정
				x += 4*direction;
				
				//엘리베이터 탑승
				if(x < 0 && direction < 0) {
					direction = 1;
					x = 4;
					y = dst_y ;//-64

					//집으로
				}else if(x > FRAME_WIDTH) { 
					this.isMoving = false;
					Thread.sleep(2000);
					this.health = maxHealth;
					x = 800-64;
					y = 2600;

				//목적지 도착
				}else if(x == dst_x && y == dst_y) {
					Thread.sleep(2000);
					System.out.println("도착");
					this.dst_x =-1;
					this.dst_y =-1;
					this.frameNum = 0;
					this.isMoving = false;
					this.rest(1);
					
				}


				draw.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		System.out.println("이동종료");
	}


}
