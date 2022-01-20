package system.Tab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import system.Struct.Company;
import system.UI.GameUI;

public abstract class Tab extends JInternalFrame implements GameUI{
	/*필드 */
	protected Container container;
	protected JPanel topPanel;
	protected JPanel centerPanel;
	protected JPanel bottomPanel;
	protected Company com;
	protected JButton btn_confirm = new JButton("확인");
	protected JButton btn_cancel = new JButton("취소");
	Tab tab;
	
	public Tab(String tabTitle){
		initialize(tabTitle);
		this.com = null;
	}
	public Tab(String tabTitle, Company com){
		initialize(tabTitle);
		this.com = com;
	}
	
	private void initialize(String tabTitle){
		/*InternalFrame 설정 */
		this.setVisible(true);
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds((FRAME_WIDTH-TAB_WIDTH)/2, (FRAME_HEIGHT-TAB_HEIGHT)/2, TAB_WIDTH, TAB_HEIGHT);
		
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		topPanel =  new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel(tabTitle));
		centerPanel = new JPanel();
		bottomPanel =  new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(btn_confirm);
		bottomPanel.add(btn_cancel);
		
		
		/*이곳에 기본 동작 추가 구현*/
		

		tab = this;
		/**/
		container.add(topPanel,BorderLayout.NORTH);
		container.add(centerPanel,BorderLayout.CENTER);
		container.add(bottomPanel,BorderLayout.SOUTH);
		
		
		/*이벤트 리스너*/
		btn_confirm.addActionListener(new confirm());
		btn_cancel.addActionListener(new cancel());
	}
	/*확인 버튼을 눌렀을때 일어날 동작을 구현*/
	public abstract void confirmOperation();
	public abstract void cancelOperation() ;
	private class confirm implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			confirmOperation();
			//layeredPane.remove(tab);
			//tab.dispose();
		}
		
	}
	private class cancel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			cancelOperation();
			layeredPane.remove(tab);
			tab.dispose();
		}
		
	}
}
