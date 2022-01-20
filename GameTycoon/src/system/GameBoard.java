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
		// �ʵ��ʱ�ȭ--------------
		frame = new JFrame();
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.getRootPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); // Ŭ���̾�Ʈ ������ ����
		frame.pack();
		
		frame.setFocusable(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		com = new Company("���� ����ȸ��", new Developer("����", 100, 10), 0);
		draw.initialize(com);
		

		// �޼ҵ� �ʱ�ȭ--------------
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
		
		
		view.addMouseMotionListener(new ViewMouseMotionAdapter());	//ȭ�� ��ũ�� ������
		
		
		
		view.addMouseListener(new MouseAdapter() {					//ȭ�� Ŭ�� ������
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
	 * ���� �ð� ���õ� �Լ�
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
                        //���� �ൿ����;
                    }
                    if (++hour == 24) {
                        // �ð� �߰�
                        com.setTime(1);

                        com.sellGame();


                        //������ ���ڸ��� ���ٸ�
                        //������ ü���� ���ٸ�
                        for(Developer dev : com.getDevList()) {
                            if(!dev.isMoving && dev.x != dev.deskPos.x) //���� �����Ұ�
                                dev.goDesk();
                            if(dev.getHealth() == 0)
                                dev.goHome();
                        }
                        //com.getDev(0).setX(com.getDev(0).getX()+2);

                        // 30�� ����
                        if (com.getTime() % 30 == 0) {
                            // û���ݾ�(�Ӵ�� + �����ΰǺ� + ������������)
                            if (com.adjustment())
                                delinquencyStack++;
                            else
                                delinquencyStack = 0;
                        }

                        // �������� ������Ʈ�� �����Ѵٸ� ����
                        if (com.getProjectCount() != 0) {
                            com.progressProject();
                        }
                        if (delinquencyStack == 3) {
                            JOptionPane.showMessageDialog(null, "�Ļ�");
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
        //���� ���� �� �۸�
        if(x < 140 || x > 780 ||y<250*(11-com.getFloor()))
            return;       
        int row= (y/250)%11;
        int col =((x-140)/64)%10;

        for(Item item:com.getItemList()) {
            if(item.xPos == col && item.yPos == row&&
               item.getName()=="å��"&&
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
                    JOptionPane.showMessageDialog(null, "�̹� �������� �����մϴ�");
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
