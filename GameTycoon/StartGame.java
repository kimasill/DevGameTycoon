package system.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StartGame extends JPanel implements Runnable,GameUI  {
	private static final int titleFrame = 2;
	private boolean isLoading = true;
	private ImageIcon []title = new ImageIcon[titleFrame];
	private int titleY = -100;
	private int frameNum = 0;
	
	private JButton btnNew = new JButton("�� ����");
	private JButton btnContinue = new JButton("�̾��ϱ�");
	private JButton btnClose = new JButton("����");
	private JLabel titleLogo = new JLabel("");
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
		this.add(titleLogo);
		btnNew.setBounds(400 - 100, 300, 200, 50);
		btnContinue.setBounds(400 - 100, 380, 200, 50);
		btnClose.setBounds(400 - 100, 460, 200, 50);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.remove(0);
				
			}
			
		});
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
			
		});
		
		
		for(int i = 0 ; i < titleFrame ; i++) {
			title[i] = new ImageIcon("image/title"+(i)+".png");
		}
		
	}

	@Override
	public void run() {
		int cnt = 0;
		while(isLoading) {
			try {
				repaint();
				
				Thread.sleep(10);
				titleY += 2;
				
				if(cnt++ == 20) {	//�ΰ� ȭ�� ��ȯ
					
					if (++frameNum== titleFrame)
						frameNum = 0;
					cnt = 0;
				}

				if(titleY >= 100) {
					isLoading = false;
				}
				titleLogo.setIcon(title[frameNum]);
				titleLogo.setLocation(titleLogo.getX(), titleY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.add(btnNew);
		this.add(btnContinue);
		this.add(btnClose);
		
		repaint();
	}
}
