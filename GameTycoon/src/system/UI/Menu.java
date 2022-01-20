package system.UI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.GameBoard;
import system.cheatkeyAdapter;
import system.Struct.Company;
import system.Tab.CheatTab;
import system.Tab.DevTab;
import system.Tab.GameDevTab;
import system.Tab.InfoTab;
import system.Tab.ItemTab;
import system.Tab.LoadTab;
import system.Tab.Tab;
import system.Tab.TabType;

public class Menu implements GameUI, Updateable {

	private Company com;
	private JButton toggleButton;

	private JButton menuBar_btn2;
	private JButton menuBar_btn1;
	private JButton menuBar_btn6;
	private JButton menuBar_btn3;
	private JButton menuBar_btn4;
	private JButton menuBar_btn5;
	private JButton menuBar_btn7;
	
	public Menu(Company company) {
		this.com = company;

		layeredPane.setLayer(menu, 100);
		menu.setBounds(layeredPane.getWidth() - 236, layeredPane.getHeight() - 321 + 32, 236, 321);
		layeredPane.add(menu);
		menu.setBackground(Color.GRAY);
		menu.setVisible(false);
		menu.setLayout(null);

		toggleButton = new JButton("메뉴");
		toggleButton.setVisible(true);
		toggleButton.setBounds(layeredPane.getWidth() - 64, layeredPane.getHeight(), 64, 32);
		
		layeredPane.setLayer(toggleButton, 100);
		layeredPane.add(toggleButton);

		toggleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (menu.isVisible()) {
					menu.setVisible(false);
					toggleButton.setLocation(toggleButton.getX() + 236, toggleButton.getY());
				} else {
					menu.setVisible(true);
					toggleButton.setLocation(toggleButton.getX() - 236, toggleButton.getY());
				}
			}
		});
		toggleButton.setBackground(Color.GRAY);
		
		JPanel menuBar_panel = new JPanel();
		menuBar_panel.setOpaque(false);
		menuBar_panel.setBounds(12, 10, 212, 301);
		menu.add(menuBar_panel);
		menuBar_panel.setLayout(new GridLayout(6, 0, 5, 5));

		menuBar_btn1 = new JButton("회사 정보");
		menuBar_btn2 = new JButton("게임 개발");
		menuBar_btn3 = new JButton("아이템 구매");
		menuBar_btn4 = new JButton("직원 고용");
		menuBar_btn5 = new JButton("저장");
		menuBar_btn6 = new JButton("불러오기");
		menuBar_btn7 = new JButton("종료");

		menuBar_btn1.addActionListener(new MenuButtonListener(TabType.INFO));
		menuBar_panel.add(menuBar_btn1);

		menuBar_btn2.addActionListener(new MenuButtonListener(TabType.GAMEDEV));
		menuBar_panel.add(menuBar_btn2);

		menuBar_btn3.addActionListener(new MenuButtonListener(TabType.ITEM));
		menuBar_panel.add(menuBar_btn3);

		menuBar_btn4.addActionListener(new MenuButtonListener(TabType.DEVELOPER));
		menuBar_panel.add(menuBar_btn4);

		menuBar_btn5.addActionListener(new MenuButtonListener(TabType.SAVE));
		menuBar_panel.add(menuBar_btn5);

		menuBar_btn6.addActionListener(new MenuButtonListener(TabType.LOAD));
		menuBar_panel.add(menuBar_btn6);

		menuBar_btn7.addActionListener(new MenuButtonListener(TabType.EXIT));
		menuBar_panel.add(menuBar_btn7);
		toggleButton.addKeyListener(new cheatkeyAdapter());					//치트탭 리스너
	}

	// e.getSource로 변경 할
	class MenuButtonListener implements ActionListener {
		TabType t;

		public MenuButtonListener(TabType t) {
			this.t = t;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			e.getSource();
			if (layeredPane.getComponentCountInLayer(JLayeredPane.POPUP_LAYER) != 0)
				return;
			Tab tab = null;
			switch (t) {
			case INFO:
				tab = new InfoTab(com);
				break;
			case GAMEDEV:
				tab = new GameDevTab(com);
				break;
			case DEVELOPER:		
				tab = new DevTab(com);		
				break;
			case ITEM:			
				tab = new ItemTab(com);				
				break;
			case SAVE:				
				String saveName = null;
				
				saveName = JOptionPane.showInputDialog("세이브 파일명을 입력해주세요");					
				if(saveName!=null) {
					if(com.saveFile(saveName)) {
					JOptionPane.showMessageDialog(null, "저장되었습니다.");
					}
				}
				
				break;
			case LOAD:	
				tab = new LoadTab(com);				
				
				break;	
			case EXIT:
				if (JOptionPane.showInternalConfirmDialog(null, "종료하시겠습니까?", "종료",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					System.exit(0);
				break;
			default:
				// 에러처리할것

				break;
			}
			if (tab != null)
				layeredPane.add(tab, JLayeredPane.POPUP_LAYER);
		}
	}
	class cheatkeyAdapter extends KeyAdapter {
		private String keyword = "cheat";
		private char[] buf;
		private char[] str;
		int p = 0; // 다음 글자 포인터임

		cheatkeyAdapter() {
			this.str = keyword.toCharArray();
			this.buf = new char[str.length];
		}

		public void keyPressed(KeyEvent e) {
			if ((buf[p] = e.getKeyChar()) == str[str.length - 1]) {
				if (++p == str.length)
					p = 0;
				char c;
				int cnt = 0;
				while (cnt < 5) {
					if (p - cnt - 1 < 0)
						c = buf[p - cnt - 1 + str.length];
					else
						c = buf[p - cnt - 1];

					if (str[str.length - 1 - cnt] != c)
						break;

					if (++cnt == 5) {
						layeredPane.add(new CheatTab(com), JLayeredPane.POPUP_LAYER);
						cnt = 0;
						break;
					}
				}
			} else {
				if (++p == str.length)
					p = 0;
			}

		}

	}
}
