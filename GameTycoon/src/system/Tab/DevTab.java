package system.Tab;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import system.Struct.Company;
import system.Struct.Developer;
import system.UI.GameUI;

public class DevTab extends Tab implements GameUI{
	
	private ArrayList<Developer> DevList = new ArrayList<Developer>(4);
	public DevTab(Company com) {
		super("직원 고용",com);		
		
		this.DevList = com.getDevSellList();
		for(Developer Dev : DevList ) {			
			JLabel DevName = new JLabel(Dev.toString());
			DevName.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					Dev.setSelectToggle(true);					
					JOptionPane.showMessageDialog(null, "선택되었습니다.");
				}
			});
			centerPanel.add(DevName);			
		}		
	}

	@Override
	public void confirmOperation() {
		// TODO Auto-generated method stub
		for(Developer Dev : DevList) {
			if(Dev.getSelectToggle()) {					
				com.addDevList(Dev);	
				this.doDefaultCloseAction();
				layeredPane.remove(tab);
				break;
			}
		}
	}

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
	}
}
