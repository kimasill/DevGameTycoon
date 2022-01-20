package system.Tab;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.Item.Desk;
import system.Item.Item;
import system.Struct.Company;
import system.Struct.Developer;
import system.UI.DrawOperator;

public class DevSetTab extends Tab {

	JPanel devPanel;
	private Developer selectDev = null;
	private Desk desk;

	public DevSetTab(Company com, Desk desk) {
		super("���� ��ġ", com);
		this.desk = desk;
		initialize();
	}

	public void initialize() {

		devPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 1, 0, 0));
		centerPanel.add(devPanel);
		devPanel.setLayout(new FlowLayout());
		devPanel.add(new JLabel("����"));
		printDevList();
	}

	public void printDevList() {
		if (com.getDevList() == null)
			return;

		for (int i = 0; i < com.getDevList().size(); i++) {
			Developer Dev = com.getDevList().get(i);
			JLabel showDev = new JLabel(Dev.getName());
			System.out.printf(Dev.toString());
			devPanel.add(showDev);
			showDev.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (Dev.getIsPlaced() == 0) {

						selectDev = Dev;
						JOptionPane.showMessageDialog(null, "���õǾ����ϴ�. Ȯ���� ������ ��ġ�˴ϴ�.");
					}
				}
			});
		}
	}

	@Override
	public void confirmOperation() {
		// TODO Auto-generated method stub
		if (selectDev != null)
			selectDev.setIsPlaced(1);
		for (Developer Dev : com.getDevList()) {
			if (Dev.getIsPlaced() == 1) {
				desk.setOwner(Dev);
				for (Item item : com.getItemList()) {
					if (item == (Item) desk) {
						Dev.setDesk(item.xPos, item.yPos);
					}
				}
				draw.repaint();
				Dev.setIsPlaced(2);
				new JOptionPane();
				JOptionPane.showMessageDialog(null, "å���� ������ �����Ǿ����ϴ�" + "���� :" + Dev.toString());
				desk.setIsPlaced(3);// ������ ����
				break;
			}
		}
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
	}

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub
		selectDev = null;
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
	}
}
