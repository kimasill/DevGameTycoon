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


public class DevEventTab extends Tab implements GameUI{
    private JPanel panel = new JPanel();
    private	EventScript event = EventScript.getEventScript();
    private JTextArea text = new JTextArea();
    private boolean answer = false;
    private int n;
    public DevEventTab(Company com, int n){
        super("�̺�Ʈ �߻�",com);
        this.n = n;
        if(Math.random()<0.5)
        	answer = true;
        String answerScript;
        if(answer) {
        	answerScript = event.getSuccessScript();
        }
        
        btn_confirm.setText("	��");
        btn_cancel.setText("�ƴϿ�");
        text.setEnabled(false);
        text.setAlignmentY(100);
        text.setText(event.getMainScript());
        text.setMargin(new Insets(10,10,10,10));
        text.setEditable(false);
        text.setAutoscrolls(true);
        text.setPreferredSize(new Dimension(400,250));
        text.setLineWrap(true);
        panel.setLayout(new FlowLayout());
        
        panel.add(new JLabel());
        panel.add(text);


        centerPanel.add(panel);
    }

    @Override
    public void confirmOperation() {
        // TODO Auto-generated method stub
    	if(answer) {
			JOptionPane.showMessageDialog(this, event.getSuccessScript() + "\n �������൵�� �����˴ϴ�.");
			com.getProject(n).setProgress(event.getProgress());
		}else {
			JOptionPane.showMessageDialog(this, event.getFailScript() + "\n �������൵�� ���ҵ˴ϴ�.");
			com.getProject(n).setProgress(-event.getProgress());
		}
        this.doDefaultCloseAction();

    }

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub
		
		if(!answer) {
			JOptionPane.showMessageDialog(this, event.getSuccessScript() + "\n �������൵�� �����˴ϴ�.");
			com.getProject(n).setProgress(-event.getProgress());
		}else {
			JOptionPane.showMessageDialog(this, event.getFailScript() + "\n �������൵�� ���ҵ˴ϴ�.");
			com.getProject(n).setProgress(event.getProgress());
		}
	}
}