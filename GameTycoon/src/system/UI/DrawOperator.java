
package system.UI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import system.Item.Item;
import system.Struct.Company;
import system.Struct.Developer;

/*
 * 맵의 Graphic 요소를 그리는 클래스.
 * drawPanel 객체를 맵 요소를 그릴때 마다 생성하여 사용 	
 * or 객체생성하지 않고 repaint() 하여 사용
 * 두방법다 구동확인(repaint() 사용은 버그있음 사용X)
 */

public class DrawOperator extends JPanel implements GameUI {
	
	/*
	 * 배경화면
	 */
	private Image backGround = Toolkit.getDefaultToolkit().getImage("image/배경2.jpg");	
	private Image floor = Toolkit.getDefaultToolkit().getImage("image/Floor.png");
	
	
//	private Image massageChair = Toolkit.getDefaultToolkit().getImage("image/flower1");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Company com;
	

	
	public DrawOperator() {};
	public void initialize(Company com) {
		this.com = com;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/*
		 * 배경화면 그리는 부분
		 */
		g.drawImage(backGround,0,0,800,2750,this);
		/*
		 * 맵 tile 그리는부분
		 */
		for(int i = 1;i<=com.getFloor();i++ ) {				
			g.drawImage(floor,0,(11-i)*250-32,800,250, this);					
		}								
		
		for(Item item : com.getItemList()) {
			if(item.xPos!=-1&&item.yPos!=-1) {
				Image itemImage = Toolkit.getDefaultToolkit().getImage("image/"+ item.getAdress()+".png");
				g.drawImage(itemImage, (item.xPos)*64+140, (item.yPos)*250+50,64,128,this);	
			}
		}
		
		for(Developer dev : com.getDevList()) {
			if(dev.x!=-1 && dev.y!=-1) {
				if(dev.direction > 0) {
					g.drawImage(dev.getSprite(), dev.x, dev.y+10, dev.x+64, dev.y+64+10,dev.frameNum*32, 0, (dev.frameNum+1)*32,32, this);
				}
				else {
					g.drawImage(dev.getSprite(), dev.x+64, dev.y+10, dev.x, dev.y+64+10,dev.frameNum*32, 0, (dev.frameNum+1)*32,32, this);
				}
			}				
		}	
		
		
		
		

		
				
				
		g.dispose();
	}

}
