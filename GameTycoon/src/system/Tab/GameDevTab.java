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
        super("게임 개발",com);
        
        
        titleInput = new JTextField();
        
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setPreferredSize(new Dimension(400,200));
        
        JLabel label_7 = new JLabel("타이틀 이름");
        panel.add(label_7);
        panel.add(titleInput);

        panel.add(new JLabel("주제 선택"));
        comboBox1 = new JComboBox<Subject>(Subject.values());
        comboBox1.setSelectedItem(null);
        panel.add(comboBox1);
        panel.add(new JLabel("장르선택"));
        comboBox2 = new JComboBox<Genre>(Genre.values());
        comboBox2.setSelectedItem(null);
        panel.add(comboBox2);
        panel.add(new JLabel("개발자 선택"));
        
        
        
        
        
        comboBox3 = new JComboBox<Developer>(com.getFreeDevList());
        comboBox3.setSelectedItem(null);
        panel.add(comboBox3);
        
        JPanel panel2 = new JPanel(new FlowLayout());
        JLabel info = new JLabel();
        panel2.setPreferredSize(new Dimension(400,200));
        panel2.setBackground(Color.white);
        
        panel2.add(new JLabel("개발 정보\n"));
        panel2.add(info);
        centerPanel.add(panel);
        centerPanel.add(panel2);
        
        comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subject = (Subject) comboBox1.getSelectedItem();
				
				try {
					info.setText("개발예산 : " + subject.getCost() +" + "+ genre.getCost());
				}catch(NullPointerException ex) {
					
				}
			}
        });
        comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				genre = (Genre) comboBox2.getSelectedItem();
				try {
					info.setText("개발예산 : " + subject.getCost() +" + "+ genre.getCost());
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
		    		JOptionPane.showMessageDialog(null, "더 이상 직원을 선택할 수 없습니다.");
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
        	JOptionPane.showMessageDialog(this, "개발을 시작합니다.");
            tab.dispose();
    		layeredPane.remove(tab);
            return;
        }else if(res == 1){
        	JOptionPane.showMessageDialog(this, "자금이 부족합니다.");
        }else if(res == 2){
        	JOptionPane.showMessageDialog(this, "최소한 한명의 개발자를 배정해야합니다.");
        }else if(res == 3){
        	JOptionPane.showMessageDialog(this, "다른 타이틀명을 작성해주세요");
        }else if(res == 4){
        	JOptionPane.showMessageDialog(this, "더는 개발할 수 없습니다.");
        }else if(res == 5){
        	JOptionPane.showMessageDialog(this, "주제와 장르를 선택하십시오.");
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