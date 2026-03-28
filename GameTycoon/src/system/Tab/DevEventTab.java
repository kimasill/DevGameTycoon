package system.Tab;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import system.Struct.Company;
import system.UI.GameUI;

public class DevEventTab extends Tab implements GameUI {
	private JPanel panel = new JPanel();
	private EventScript event = EventScript.getEventScript();
	private JTextArea text = new JTextArea();
	private boolean answer = false;
	private int n;

	public DevEventTab(Company com, int n) {
		super("\uAC1C\uBC1C \uC774\uBCA4\uD2B8", com);
		this.n = n;
		if (Math.random() < 0.5)
			answer = true;

		btn_confirm.setText("\uC608");
		btn_cancel.setText("\uC544\uB2C8\uC624");
		text.setEnabled(false);
		text.setAlignmentY(100);
		text.setText(event.getMainScript());
		text.setMargin(new Insets(10, 10, 10, 10));
		text.setEditable(false);
		text.setAutoscrolls(true);
		text.setPreferredSize(new Dimension(400, 250));
		text.setLineWrap(true);
		panel.setLayout(new FlowLayout());

		panel.add(new JLabel());
		panel.add(text);

		centerPanel.add(panel);
	}

	@Override
	public void confirmOperation() {
		if (answer) {
			JOptionPane.showMessageDialog(this,
					event.getSuccessScript() + "\n\uAC1C\uBC1C \uC9C4\uD589\uB3C4\uAC00 \uBCC0\uB3D9\uD569\uB2C8\uB2E4.");
			com.getProject(n).setProgress(event.getProgress());
			com.appendMoney(event.getMoney());
		} else {
			JOptionPane.showMessageDialog(this,
					event.getFailScript() + "\n\uAC1C\uBC1C \uC9C4\uD589\uB3C4\uAC00 \uBCC0\uB3D9\uD569\uB2C8\uB2E4.");
			com.getProject(n).setProgress(-event.getProgress());
		}
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
		this.dispose();
	}

	@Override
	public void cancelOperation() {
		if (!answer) {
			JOptionPane.showMessageDialog(this,
					event.getSuccessScript() + "\n\uAC1C\uBC1C \uC9C4\uD589\uB3C4\uAC00 \uBCC0\uB3D9\uD569\uB2C8\uB2E4.");
			com.getProject(n).setProgress(-event.getProgress());
		} else {
			JOptionPane.showMessageDialog(this,
					event.getFailScript() + "\n\uAC1C\uBC1C \uC9C4\uD589\uB3C4\uAC00 \uBCC0\uB3D9\uD569\uB2C8\uB2E4.");
			com.getProject(n).setProgress(event.getProgress());
		}
	}
}
