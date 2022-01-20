package system;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JViewport;

import system.Item.Desk;
import system.Item.Item;
import system.Struct.Company;
import system.Struct.Developer;
import system.Struct.Rule;
import system.Tab.CheatTab;
import system.Tab.DevSetTab;
import system.Tab.Tab;
import system.UI.GameUI;
import system.UI.Menu;
import system.UI.ProgressBar;
import system.UI.StartGame;
import system.UI.StatusBar;
import system.UI.Updateable;

public class GameBoard extends Thread implements GameUI, Updateable {

	private JFrame frame;
	private Company com;
	private Point viewPoint;
	private JViewport view = new JViewport();
	private int delinquencyStack = 0;
	private boolean isRun = true;
	private int sleepTime = 10;
	
	
	private Rule rule = new Rule();
	
	

	public GameBoard() {
		// 필드초기화--------------
		frame = new JFrame();
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.getRootPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); // 클라이언트 사이즈 조정
		frame.pack();
		
		frame.setFocusable(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		com = new Company("나의 게임회사", new Developer("본인", 100, 10), 0);
		draw.initialize(com);
		

		// 메소드 초기화--------------
		StartGame start = new StartGame();
		frame.setContentPane(start);
		start.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.print("asadsd");
				frame.setContentPane(layeredPane);
				System.out.print("asadsd");
				initialize();
			}
		});

		
	}

	private void initialize() {

		layeredPane.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT - STATUSBAR_HEIGHT);
		layeredPane.add(view, 0);		
		
		draw.setPreferredSize(new Dimension(FRAME_WIDTH, 2750));
		draw.setLayout(null);
		draw.setBackground(Color.GREEN);		
		
		viewPoint = new Point(0, 2750-FRAME_HEIGHT);
		
		view.setBounds(0, STATUSBAR_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT - STATUSBAR_HEIGHT);
		view.setView(draw);
		
		view.setViewPosition(viewPoint);
		view.setAutoscrolls(true);
		view.setSize(FRAME_WIDTH,FRAME_HEIGHT-STATUSBAR_HEIGHT);
		
		
		view.addMouseMotionListener(new ViewMouseMotionAdapter());	//화면 스크롤 리스너
		
		
		
		view.addMouseListener(new MouseAdapter() {					//화면 클릭 리스너
            public void mouseClicked(MouseEvent e) {
            	
                int mouseX = viewPoint.x +e.getX();
                int mouseY = viewPoint.y +e.getY();
                
                
                drawMap_test(mouseX,mouseY);
            }
        });
		new Menu(com);
		new ProgressBar(com);
		new StatusBar(com);
		this.start();
	}

	/*
	 * 게임 시간 관련된 함수
	 */
	@Override
	public void run() {
		int hour = 0;
		try {
			while (true) {
				sleep(sleepTime);

				// System.out.println(layeredPane.getComponentCountInLayer(JLayeredPane.POPUP_LAYER)+"");
				if (isRun && !menu.isVisible() && layeredPane.getComponentCountInLayer(JLayeredPane.POPUP_LAYER) == 0) {

                    if (hour == 12) {
                        //직원 행동변경;
                    }
                    if (++hour == 24) {
                        // 시간 추가
                        com.setTime(1);

                        com.sellGame();


                        //직원이 제자리에 없다면
                        //직원의 체력이 없다면
                        for(Developer dev : com.getDevList()) {
                            if(!dev.isMoving && dev.x != dev.deskPos.x) //조건 수정할것
                                dev.goDesk();
                            if(dev.getHealth() == 0)
                                dev.goHome();
                        }
                        //com.getDev(0).setX(com.getDev(0).getX()+2);

                        // 30일 마다
                        if (com.getTime() % 30 == 0) {
                            // 청구금액(임대로 + 직원인건비 + 아이템유지비)
                            if (com.adjustment())
                                delinquencyStack++;
                            else
                                delinquencyStack = 0;
                        }

                        // 진행중인 프로젝트가 존재한다면 진행
                        if (com.getProjectCount() != 0) {
                            com.progressProject();
                        }
                        if (delinquencyStack == 3) {
                            JOptionPane.showMessageDialog(null, "파산");
                            System.exit(0);
                        } 
                        if(com.getItemTabTime()+13<com.getTime()) {
	    					com.setItemSellList();
	    					com.setItemTabTime(com.getTime());
    					if(com.getDevTabTime()+13<com.getTime()) {
        					com.setDevSellList();
        					com.setDevTabTime(com.getTime());
    					}
    				} 

                        hour = 0;
                    }
                                      
                }

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//-------------------------------------------------------------
	public void drawMap_test(int x,int y) {
        //지정 범위 외 글릭
        if(x < 140 || x > 780 ||y<250*(11-com.getFloor()))
            return;       
        int row= (y/250)%11;
        int col =((x-140)/64)%10;

        for(Item item:com.getItemList()) {
            if(item.xPos == col && item.yPos == row&&
               item.getName()=="책상"&&
               item.getIsPlaced()==2) {
                Tab tab = new DevSetTab(com,(Desk)item);
                layeredPane.add(tab,JLayeredPane.POPUP_LAYER);
                break;
            }
            else if(item.xPos==col&&item.yPos==row) {
                JOptionPane.showMessageDialog(null, item.getName());
            }
            if(item.getIsPlaced()==1) {
                if(item.xPos==col&&item.yPos==row) {
                    JOptionPane.showMessageDialog(null, "이미 아이템이 존재합니다");
                    return;
                }
                item.xPos=col;
                item.yPos=row;
                frame.repaint();
                item.setIsPlaced(2);
                com.setStatus3(item.getStatus3());
                break;
                }
            }
        }
	
	

	class ViewMouseMotionAdapter extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
        	
			int y = e.getY();
			int y_dis = 0;

			if (y < 64) {
				y_dis = y - 64;
				
			} else if (y > FRAME_HEIGHT-STATUSBAR_HEIGHT - 64) {
				y_dis =  y -(FRAME_HEIGHT-STATUSBAR_HEIGHT-64);
			}

			viewPoint.y += y_dis;
			if (viewPoint.y < 0 || viewPoint.y > 2750-FRAME_HEIGHT) {
				viewPoint.y -= y_dis;
			}
			view.setViewPosition(viewPoint);
		}
	}

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameBoard window = new GameBoard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
