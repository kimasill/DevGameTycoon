package system.Tab;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.Item.Item;
import system.Struct.Company;
import system.Struct.Developer;

public class InfoTab extends Tab {
	JPanel itemPanel;
	JPanel devPanel;

	public InfoTab(Company com) {
		super("회사 정보", com);
		initialize();
	}

	public void initialize() {
		itemPanel = new JPanel();
		devPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4,1,0,0));
		centerPanel.add(new JLabel("보유 아이템",JLabel.CENTER));
		itemPanel.setBounds(12, 10, 291, 294);
		centerPanel.add(itemPanel);
		centerPanel.add(new JLabel("직원",JLabel.CENTER));
		centerPanel.add(devPanel);
		itemPanel.setLayout(new FlowLayout());		
		devPanel.setLayout(new FlowLayout());		
		printItemList();
		printDevList();
	}

	public void printItemList() {
		if (com.getItemList() == null) 
			return;
		
		for (int i = 0; i < com.getItemList().size(); i++) {
			Item item = com.getItemList().get(i);			
					
			ImageIcon itemIcon = new ImageIcon("image/"+item.getTabAdress()+".png");
			JLabel iconImg = new JLabel(itemIcon);					
			itemPanel.add(iconImg);
			iconImg.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (item.getIsPlaced() == 0) {
						item.setIsPlaced(1);							
						JOptionPane.showMessageDialog(null, "선택되었습니다.");							
						tab.doDefaultCloseAction();
						layeredPane.remove(tab);
					}
				}
			});
		}
	}
	public void printDevList() {
		if(com.getDevList()==null)
			return;
			
		for (int i = 0; i < com.getDevList().size(); i++) {
			Developer Dev = com.getDevList().get(i);
			JLabel showDev = new JLabel(Dev.getName());	
			System.out.printf(Dev.toString());
			devPanel.add(showDev);			
		}
	}

	@Override
	public void confirmOperation() {
		// TODO Auto-generated method stub
		this.doDefaultCloseAction();
		layeredPane.remove(tab);		
	}

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub
		this.doDefaultCloseAction();
		layeredPane.remove(tab);			
	}
}
