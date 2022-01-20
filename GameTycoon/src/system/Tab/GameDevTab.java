package system.Tab;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import system.Struct.Company;
import system.Struct.Developer;
import system.Struct.Genre;
import system.Struct.Rule;
import system.Struct.Subject;
import system.UI.GameUI;


public class GameDevTab extends Tab implements GameUI{
    private JTextField titleInput;
    private JComboBox<Subject> comboBox1;
    private JComboBox<Genre> comboBox2;
    private JComboBox<Developer> comboBox3;
    private JPanel panel = new JPanel();
    
    private Developer[] team = new Developer[3];
    private int devCnt = 0;
    private Subject subject;
    private Genre genre;
    private String title;
    public GameDevTab(Company com){
        super("���� ����",com);
        
        
        titleInput = new JTextField();
        
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setPreferredSize(new Dimension(400,200));
        
        JLabel label_7 = new JLabel("Ÿ��Ʋ �̸�");
        panel.add(label_7);
        panel.add(titleInput);

        panel.add(new JLabel("���� ����"));
        comboBox1 = new JComboBox<Subject>(Subject.values());
        comboBox1.setSelectedItem(null);
        panel.add(comboBox1);
        panel.add(new JLabel("�帣����"));
        comboBox2 = new JComboBox<Genre>(Genre.values());
        comboBox2.setSelectedItem(null);
        panel.add(comboBox2);
        panel.add(new JLabel("������ ����"));
        
        
        
        
        
        comboBox3 = new JComboBox<Developer>(com.getFreeDevList());
        comboBox3.setSelectedItem(null);
        panel.add(comboBox3);
        
        JPanel panel2 = new JPanel(new FlowLayout());
        JLabel info = new JLabel();
        panel2.setPreferredSize(new Dimension(400,200));
        panel2.setBackground(Color.white);
        
        panel2.add(new JLabel("���� ����\n"));
        panel2.add(info);
        centerPanel.add(panel);
        centerPanel.add(panel2);
        
        comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subject = (Subject) comboBox1.getSelectedItem();
				
				try {
					info.setText("���߿��� : " + subject.getCost() +" + "+ genre.getCost());
				}catch(NullPointerException ex) {
					
				}
			}
        });
        comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				genre = (Genre) comboBox2.getSelectedItem();
				try {
					info.setText("���߿��� : " + subject.getCost() +" + "+ genre.getCost());
				}catch(NullPointerException ex) {
					
				}
			}
        	
        });
        comboBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					team[devCnt++] = (Developer) comboBox3.getSelectedItem();
					comboBox3.removeItem(comboBox3.getSelectedItem());
				}catch(ArrayIndexOutOfBoundsException ex) {
		    		JOptionPane.showMessageDialog(null, "�� �̻� ������ ������ �� �����ϴ�.");
				}
				info.setText(info.getText() + comboBox3.getSelectedItem().toString());
			}
        	
        });


    }

    @Override
    public void confirmOperation() {
        // TODO Auto-generated method stub
    	title = titleInput.getText();
    	subject = (Subject) comboBox1.getSelectedItem();
    	genre = (Genre)comboBox2.getSelectedItem();
    	
    	int res;
        if((res = com.startDev(title, subject, genre, team)) == 0) {
        	JOptionPane.showMessageDialog(this, "������ �����մϴ�.");
            tab.dispose();
    		layeredPane.remove(tab);
            return;
        }else if(res == 1){
        	JOptionPane.showMessageDialog(this, "�ڱ��� �����մϴ�.");
        }else if(res == 2){
        	JOptionPane.showMessageDialog(this, "�ּ��� �Ѹ��� �����ڸ� �����ؾ��մϴ�.");
        }else if(res == 3){
        	JOptionPane.showMessageDialog(this, "�ٸ� Ÿ��Ʋ���� �ۼ����ּ���");
        }else if(res == 4){
        	JOptionPane.showMessageDialog(this, "���� ������ �� �����ϴ�.");
        }else if(res == 5){
        	JOptionPane.showMessageDialog(this, "������ �帣�� �����Ͻʽÿ�.");
        }else {
        	JOptionPane.showMessageDialog(this, "?");
        }
        return;

    }

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub
		this.doDefaultCloseAction();
		layeredPane.remove(tab);
		
	}
}