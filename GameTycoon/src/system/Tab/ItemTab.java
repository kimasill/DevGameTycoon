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
		super("������ ����", com);

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
			JLabel infoLabel = new JLabel("<html>"+item.getName()+"<br>"+"����:"+item.getCost()+"����"
				+"<br>"+"������:"+item.getStatus1()+"<br>"+"�Ƿε�:"+item.getStatus2()+"<br>"+"ȿ��:"+item.getStatus3());
			
			JButton itemButton = new JButton("����");
			itemButton.setSize(64, 32);
			
			imgPanel.add(imgLabel);
			infoPanel.add(infoLabel);
			btnPanel.add(itemButton);
			
			itemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(item.getSelectToggle()==false) {
					JOptionPane.showMessageDialog(null, "�̹� �����Ͽ����ϴ�.");
				}
					
				else if (com.getMoney() > item.getCost()) {
					com.appendMoney(-item.getCost());
					com.addItemList(item);
					item.setSelectToggle(false);
					itemList.remove(item);
					imgPanel.remove(imgLabel);
					infoPanel.remove(infoLabel);
					btnPanel.remove(itemButton);
					JOptionPane.showMessageDialog(null, "�����Ͽ����ϴ�.");			
				
				}						
				else {
					JOptionPane.showMessageDialog(null, "���� ���ڶ��ϴ�.");
				}
			}
		});
			
		}
		
		
		
		
		
		Desk desk = new Desk("å��");		
		
		ImageIcon deskIcon = new ImageIcon("image/"+desk.getTabAdress()+".png");
		JLabel deskLabel = new JLabel(deskIcon,JLabel.CENTER);
		deskLabel.setBorder(BorderFactory.createEmptyBorder(5,5, 0,0));
		JLabel infoLabel = new JLabel("<html>"+desk.getName()+"<br>"+"���� : "+desk.getCost()+"����"
				//+"<br>"+"������:"+item.getStatus1()+"<br>"+"�Ƿε�:"+item.getStatus2()+"<br>"+"ȿ��:"+item.getStatus3()
				);
		JLabel floorImgLabel = new JLabel(new ImageIcon("image/Building_64,64.png"));
		floorImgLabel.setBorder(BorderFactory.createEmptyBorder(5,5, 0,0));
		JLabel floorInfoLabel = new JLabel("<html>����<br>���� : 500 ����");
		JButton btn_addFloor = new JButton("����");		
		JButton btn_addDesk = new JButton("����");
		
		btn_addFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (com.getMoney() > 10) {				///���ĺ����Ұ�@@@@@@@@@@@@@@@@@
					com.appendMoney(-10);
					com.addFloor();
					draw.repaint();
					JOptionPane.showMessageDialog(null, "�����Ͽ����ϴ�.");
				}else if(com.getFloor() > 11) {
					JOptionPane.showMessageDialog(null, "�� �̻� ������ �� �����ϴ�.");
				}else {
					JOptionPane.showMessageDialog(null, "���� ���ڶ��ϴ�.");
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
					
					JOptionPane.showMessageDialog(null, "�����Ͽ����ϴ�.");
				}else {
					JOptionPane.showMessageDialog(null, "���� ���ڶ��ϴ�.");
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
