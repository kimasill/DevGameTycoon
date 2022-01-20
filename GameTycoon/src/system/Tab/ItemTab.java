package system.Tab;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.Item.Desk;
import system.Item.Item;
import system.Struct.Company;
import system.UI.GameUI;

public class ItemTab extends Tab implements GameUI {

	private int select = 0;
	private ArrayList<Item> itemList = new ArrayList<>(3);

	public ItemTab(Company com) {
		super("아이템 구매", com);

		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(3,1));
		this.itemList = com.getItemSellList();		
		JPanel imgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,0));		
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,23,3));
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,27,3));		
		itemPanel.add(imgPanel);
		itemPanel.add(infoPanel);
		itemPanel.add(btnPanel);
		
		for(Item item : itemList) {
			ImageIcon icon = new ImageIcon("image/"+item.getTabAdress()+".png");
			JLabel imgLabel = new JLabel(icon,JLabel.CENTER);			
			JLabel infoLabel = new JLabel("<html>"+item.getName()+"<br>"+"가격:"+item.getCost()+"만원"
				+"<br>"+"유지비:"+item.getStatus1()+"<br>"+"피로도:"+item.getStatus2()+"<br>"+"효율:"+item.getStatus3());
			
			JButton itemButton = new JButton("구매");
			itemButton.setSize(64, 32);
			
			imgPanel.add(imgLabel);
			infoPanel.add(infoLabel);
			btnPanel.add(itemButton);
			
			itemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(item.getSelectToggle()==false) {
					JOptionPane.showMessageDialog(null, "이미 구매하였습니다.");
				}
					
				else if (com.getMoney() > item.getCost()) {
					com.appendMoney(-item.getCost());
					com.addItemList(item);
					item.setSelectToggle(false);
					itemList.remove(item);
					imgPanel.remove(imgLabel);
					infoPanel.remove(infoLabel);
					btnPanel.remove(itemButton);
					JOptionPane.showMessageDialog(null, "구매하였습니다.");			
				
				}						
				else {
					JOptionPane.showMessageDialog(null, "돈이 모자랍니다.");
				}
			}
		});
			
		}
		
		
		
		
		
		Desk desk = new Desk("책상");		
		
		ImageIcon deskIcon = new ImageIcon("image/"+desk.getTabAdress()+".png");
		JLabel deskLabel = new JLabel(deskIcon,JLabel.CENTER);
		deskLabel.setBorder(BorderFactory.createEmptyBorder(5,5, 0,0));
		JLabel infoLabel = new JLabel("<html>"+desk.getName()+"<br>"+"가격 : "+desk.getCost()+"만원"
				//+"<br>"+"유지비:"+item.getStatus1()+"<br>"+"피로도:"+item.getStatus2()+"<br>"+"효율:"+item.getStatus3()
				);
		JLabel floorImgLabel = new JLabel(new ImageIcon("image/Building_64,64.png"));
		floorImgLabel.setBorder(BorderFactory.createEmptyBorder(5,5, 0,0));
		JLabel floorInfoLabel = new JLabel("<html>증축<br>가격 : 500 만원");
		JButton btn_addFloor = new JButton("구매");		
		JButton btn_addDesk = new JButton("구매");
		
		btn_addFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (com.getMoney() > 10) {				///추후변경할것@@@@@@@@@@@@@@@@@
					com.appendMoney(-10);
					com.addFloor();
					draw.repaint();
					JOptionPane.showMessageDialog(null, "구매하였습니다.");
				}else if(com.getFloor() > 11) {
					JOptionPane.showMessageDialog(null, "더 이상 증축할 수 없습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "돈이 모자랍니다.");
				}
			}
		});
		
		btn_addDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (com.getMoney() > desk.getCost()) {
					com.appendMoney(-desk.getCost());

					com.addItemList(desk);
					desk.setSelectToggle(true);
					itemList.remove(desk);
					
					JOptionPane.showMessageDialog(null, "구매하였습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "돈이 모자랍니다.");
				}
			}
		});
		imgPanel.add(deskLabel);
		imgPanel.add(floorImgLabel);
		infoPanel.add(infoLabel);
		infoPanel.add(floorInfoLabel);
		btnPanel.add(btn_addDesk);		
		btnPanel.add(btn_addFloor);
		centerPanel.add(itemPanel);
		
		
		bottomPanel.remove(btn_confirm);
	}

	@Override
	public void confirmOperation() {

	}

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
	}

}
