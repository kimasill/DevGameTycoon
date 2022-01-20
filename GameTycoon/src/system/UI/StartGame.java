package system.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartGame extends JPanel implements Runnable,GameUI  {
	private static final int titleFrame = 2;
	private boolean isLoading = true;
	private ImageIcon []title = new ImageIcon[titleFrame];
	private int titleY = -100;
	private int frameNum = 0;
	
	private JLabel titleLogo = new JLabel();
	private JLabel pressToStart = new JLabel("화면을 눌러 시작하세요.");
	public StartGame(){
		init();
		
		Thread thread = new Thread(this);
		
		thread.start();
		
	}
	public void init() {
		this.setLayout(new FlowLayout());
		this.setBounds(0, 0, 800, 600);
		this.setBackground(Color.black);
		this.setVisible(true);
		titleLogo.setLocation(400-64, titleY);
		
		pressToStart.setBounds(400 - 180/2, 500, 180, 20);
		this.add(titleLogo);
		
		
		
		for(int i = 0 ; i < titleFrame ; i++) {
			title[i] = new ImageIcon("image/title"+(i)+".png");
		}
		
	}

	@Override
	public void run() {
		int cnt = 0;
		while(isLoading) {
			try {
				titleLogo.setIcon(title[frameNum]);
				titleLogo.setLocation(titleLogo.getX(), titleY);
				
				Thread.sleep(10);
				titleY += 2;
				
				if(cnt++ == 20) {	
					if (++frameNum== titleFrame)
						frameNum = 0;
					cnt = 0;
				}

				if(titleY >= 100) {
					this.add(pressToStart);
					isLoading = false;
				}
				repaint();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.add(new JLabel("화면을 눌러 게임을 시작하십시오"));
	}
}

