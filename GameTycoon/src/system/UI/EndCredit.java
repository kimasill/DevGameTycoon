package system.UI;

import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import system.Struct.Company;
import system.Struct.Developer;
import system.Struct.Game;

public class EndCredit extends Thread implements Runnable, GameUI{
	private JPanel endPanel;
	private JPanel credit;
	private Company com;
	private Image image;
	private Thread thread;
	private int labelY=210;
	private int y = GAME_FRAME_HEIGHT;
	private int i=0;
	
	public EndCredit(Company com) {					
		layeredPane.removeAll();		
		credit = new JPanel();	
		credit.setBounds(50, 0, 700, 2000);
		credit.setBackground(Color.BLACK);
		endPanel = new JPanel();	
		endPanel.setLayout(null);
		endPanel.add(credit);		
		credit.setLayout(null);
		
		JLabel endTitle = new JLabel(new ImageIcon("image/EndingTitle.png"));
		endTitle.setBounds(80, 10, 500, 200);
		credit.add(endTitle);
		endPanel.setFocusable(true);
		endPanel.setBackground(Color.cyan);
		endPanel.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
		layeredPane.add(endPanel,100);
		printDev();
//		printGameTitle();
		JLabel creditTitle = new JLabel("");
		JLabel devList = new JLabel();
		
		this.start();
	}
	public void printDev() {
		for(Developer dev : com.getDevList()) {
			JLabel gameName = new JLabel(dev.getName());
			gameName.setLocation(300, labelY);
			this.labelY += 30;
			endPanel.add(gameName,JLabel.CENTER);
		}
	}
	
	@Override
	public void run() {
		while(i<1000) {
			y = y - 5;
			credit.setLocation(55,y);
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		i++;
	}
}
