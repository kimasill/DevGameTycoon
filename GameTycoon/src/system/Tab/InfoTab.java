package system.Tab;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import system.Item.Item;
import system.Struct.Company;
import system.Struct.Developer;
import system.Struct.Game;

public class InfoTab extends Tab {
	JPanel itemPanel;
	JPanel devPanel;
	JPanel statsPanel;

	public InfoTab(Company com) {
		super("\uD68C\uC0AC \uC815\uBCF4", com);
		initialize();
	}

	public void initialize() {
		itemPanel = new JPanel();
		devPanel = new JPanel();
		statsPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 1, 0, 4));
		centerPanel.add(new JLabel("\uD68C\uC0AC \uD1B5\uACC4", JLabel.CENTER));
		statsPanel.setLayout(new GridLayout(0, 1));
		refreshStats();
		centerPanel.add(statsPanel);
		centerPanel.add(new JLabel("\uCD9C\uC2DC \uC791\uD488", JLabel.CENTER));
		JTextArea productArea = new JTextArea(buildProductSummary(), 4, 28);
		productArea.setEditable(false);
		productArea.setLineWrap(true);
		centerPanel.add(new JScrollPane(productArea));
		centerPanel.add(new JLabel("\uBCF4\uC720 \uC544\uC774\uD15C", JLabel.CENTER));
		itemPanel.setBounds(12, 10, 291, 294);
		centerPanel.add(itemPanel);
		centerPanel.add(new JLabel("\uC9C1\uC6D0", JLabel.CENTER));
		centerPanel.add(devPanel);
		itemPanel.setLayout(new FlowLayout());
		devPanel.setLayout(new FlowLayout());
		printItemList();
		printDevList();
	}

	private void refreshStats() {
		statsPanel.removeAll();
		statsPanel.add(new JLabel("\uD68C\uC0AC\uBA85: " + com.getCompanyName()));
		statsPanel.add(new JLabel("\uC790\uAE08: " + com.getMoneyToString()));
		statsPanel.add(new JLabel("\uACBD\uACFC \uC77C\uC218: " + com.getTime()));
		statsPanel.add(new JLabel("\uC778\uAE30\uB3C4: " + com.getPopularity()));
		statsPanel.add(new JLabel("\uCD9C\uC2DC \uD0C0\uC774\uD2C0 \uC218: " + com.getProducts().size()));
	}

	private String buildProductSummary() {
		StringBuilder sb = new StringBuilder();
		for (Game g : com.getProducts()) {
			sb.append(g.getTitle());
			sb.append(" | \uAD00\uC2EC\uB3C4 ").append(g.getInterest());
			sb.append(" | \uD3C9\uC810 ").append(g.getReviewScore());
			sb.append(" | \uD310\uB9E4\uAC00 ").append(g.getPrice());
			sb.append(" | \uB204\uC801 \uD310\uB9E4 ").append(g.getCumulativeSales());
			sb.append("\n");
		}
		if (sb.length() == 0)
			return "(\uC544\uC9C1 \uCD9C\uC2DC\uD55C \uAC8C\uC784\uC774 \uC5C6\uC2B5\uB2C8\uB2E4)";
		return sb.toString();
	}

	public void printItemList() {
		if (com.getItemList() == null)
			return;

		for (int i = 0; i < com.getItemList().size(); i++) {
			Item item = com.getItemList().get(i);

			ImageIcon itemIcon = new ImageIcon("image/" + item.getTabAdress() + ".png");
			JLabel iconImg = new JLabel(itemIcon);
			itemPanel.add(iconImg);
			iconImg.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (item.getIsPlaced() == 0) {
						item.setIsPlaced(1);
						JOptionPane.showMessageDialog(null, "\uC120\uD0DD\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
						tab.doDefaultCloseAction();
						layeredPane.remove(tab);
					}
				}
			});
		}
	}

	public void printDevList() {
		if (com.getDevList() == null)
			return;

		for (int i = 0; i < com.getDevList().size(); i++) {
			Developer dev = com.getDevList().get(i);
			JLabel showDev = new JLabel(dev.toString());
			devPanel.add(showDev);
		}
	}

	@Override
	public void confirmOperation() {
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
	}

	@Override
	public void cancelOperation() {
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
	}
}
