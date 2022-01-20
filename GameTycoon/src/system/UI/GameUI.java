package system.UI;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public interface GameUI {
	public static final Font font =  new Font("PFStardust", Font.PLAIN, 12);

	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 600;
	public static final int GAME_FRAME_WIDTH = 600;
	public static final int GAME_FRAME_HEIGHT = 600;
	public static final int TAB_WIDTH = 600;
	public static final int TAB_HEIGHT = 400;
	public static final int STATUSBAR_WIDTH = 800;
	public static final int STATUSBAR_HEIGHT = 32;
	
	/*
	 * 	메인 레이어
	 */	
	public static final JLayeredPane layeredPane = new JLayeredPane();
	public static final DrawOperator draw = new DrawOperator();
	/*
	 * 	메뉴
	 */
	public static final JPanel menu = new JPanel();
	
	/*
	 * 	상태바
	 */
	public static final JPanel statusPane = new JPanel();
	public static final JLabel statusPane_company = new JLabel();
	public static final JLabel statusPane_date = new JLabel();
	public static final JLabel statusPane_money = new JLabel();
	
	/*
	 * 	진행바
	 */
	public static final JPanel progressPane = new JPanel();
	public static final  JLabel project1 = new JLabel();
	public static final  JLabel project2 = new JLabel();
	public static final  JLabel project3 = new JLabel();
	public static final  JProgressBar projectProgress1 = new JProgressBar();
	public static final  JProgressBar projectProgress2 = new JProgressBar();
	public static final  JProgressBar projectProgress3 = new JProgressBar();
	

	
}
