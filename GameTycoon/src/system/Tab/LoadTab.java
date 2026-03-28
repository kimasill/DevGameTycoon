package system.Tab;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.Struct.Company;

public class LoadTab extends Tab {
	private String loadName;
	JPanel loadPanel;

	public LoadTab(Company com) {
		super("\uBD88\uB7EC\uC624\uAE30", com);
		initialize();
	}

	public void initialize() {
		loadPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 1, 0, 0));
		centerPanel.add(loadPanel);
		loadPanel.setLayout(new FlowLayout());
		loadPanel.add(new JLabel("\uD30C\uC77C\uC744 \uC120\uD0DD\uD558\uC138\uC694"));
		printFileList();
	}

	public void printFileList() {
		File dir = new File("save");
		if (!dir.exists() && !dir.mkdirs()) {
			loadPanel.add(new JLabel("(\uC800\uC7A5 \uD3F4\uB354\uB97C \uB9CC\uB4E4 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4)"));
			return;
		}
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			loadPanel.add(new JLabel("(\uC800\uC7A5\uB41C \uD30C\uC77C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4)"));
			return;
		}
		for (File info : files) {
			if (!info.isFile())
				continue;
			JLabel fileName = new JLabel(info.getName());
			loadPanel.add(fileName);
			fileName.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					loadName = info.getName();
					JOptionPane.showMessageDialog(null, "\uC120\uD0DD\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
				}
			});
		}
	}

	@Override
	public void confirmOperation() {
		if (loadName == null) {
			JOptionPane.showMessageDialog(null, "\uBD88\uB7EC\uC62C \uD30C\uC77C\uC744 \uBA3C\uC800 \uC120\uD0DD\uD558\uC138\uC694.");
			return;
		}
		try {
			if (com.loadFile(loadName)) {
				JOptionPane.showMessageDialog(null, "\uBD88\uB7EC\uC624\uAE30 \uC131\uACF5");
			} else {
				JOptionPane.showMessageDialog(null, "\uBD88\uB7EC\uC624\uAE30\uC5D0 \uC2E4\uD328\uD588\uC2B5\uB2C8\uB2E4.");
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "\uC800\uC7A5 \uB370\uC774\uD130 \uD615\uC2DD\uC774 \uB9DE\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
		}
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
	}

	@Override
	public void cancelOperation() {
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
	}
}
