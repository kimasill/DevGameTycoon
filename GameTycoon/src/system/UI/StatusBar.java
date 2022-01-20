package system.UI;

import java.awt.Color;

import javax.swing.JLabel;

import system.Struct.Company;

public class StatusBar implements GameUI{
	
	public StatusBar(Company company) {
		
		statusPane.setBounds(0, 0, STATUSBAR_WIDTH, STATUSBAR_HEIGHT);
		layeredPane.setLayer(statusPane, 0);
		layeredPane.add(statusPane);		
		
		statusPane.setBackground(Color.GRAY);
		
		statusPane_date.setText("0");
		statusPane_company.setText(company.getCompanyName());
		statusPane_money.setText(company.getMoneyToString());
		
		statusPane.add(statusPane_date);
		statusPane.add(new JLabel("일 차 "));
		statusPane.add(statusPane_company);
		statusPane.add(statusPane_money);
		statusPane.add(new JLabel("만원")); 
	}

}
