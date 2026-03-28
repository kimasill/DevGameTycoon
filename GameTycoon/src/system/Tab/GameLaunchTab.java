package system.Tab;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import system.Struct.Company;
import system.Struct.DevGame;
import system.Struct.Game;
import system.UI.GameUI;

public class GameLaunchTab extends Tab implements GameUI {
	private JPanel panel = new JPanel();
	private DevGame game;
	private int previewReview;

	public GameLaunchTab(Company com, int n) {
		super("\uAC8C\uC784 \uCD9C\uC2DC", com);
		game = com.getProject(n);
		previewReview = com.computeLaunchReview(game);
		Game g = game.getGame();
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		panel.add(new JLabel("\uC81C\uBAA9"));
		panel.add(new JLabel(game.getTitle()));
		panel.add(new JLabel("\uAD00\uC2EC\uB3C4"));
		panel.add(new JLabel(String.valueOf(g.getInterest())));
		panel.add(new JLabel("\uC8FC\uC81C"));
		panel.add(new JLabel(String.valueOf(g.getSubject())));
		panel.add(new JLabel("\uC7A5\uB974"));
		panel.add(new JLabel(String.valueOf(g.getGenre())));
		panel.add(new JLabel("\uCD9C\uC2DC \uD3C9\uC810(\uC608\uC815)"));
		panel.add(new JLabel(String.valueOf(previewReview)));
		int estPrice = estimatePrice(com, g, previewReview);
		panel.add(new JLabel("\uD310\uB9E4\uAC00(\uC608\uC815)"));
		panel.add(new JLabel(String.valueOf(estPrice)));
		panel.add(new JLabel("\uCD9C\uC2DC \uD6C4 \uC778\uAE30\uB3C4 \uBCC0\uD654"));
		panel.add(new JLabel((previewReview - 50) / 15 >= 0 ? "\uC0C1\uC2B9 \uC608\uC0C1" : "\uD558\uB77D \uAC00\uB2A5"));

		centerPanel.add(panel);
	}

	private static int estimatePrice(Company com, Game g, int review) {
		int baseCost = g.getSubject().getCost() + g.getGenre().getCost();
		return Math.max(1, baseCost / 100 + g.getInterest() / 12 + com.getPopularity() + review / 25);
	}

	@Override
	public void confirmOperation() {
		com.launchGame(game, previewReview);
		layeredPane.remove(tab);
		this.dispose();
	}

	@Override
	public void cancelOperation() {
	}
}
