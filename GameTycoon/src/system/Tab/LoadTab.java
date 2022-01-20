package system.Tab;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.Item.Item;
import system.Struct.Company;
import system.Struct.Developer;
import system.Tab.Tab;

public class LoadTab extends Tab {	
	private String loadName;
	JPanel loadPanel;
	public LoadTab(Company com) {
		super("불러오기",com);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public void initialize() {		
		loadPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,1,0,0));		
		centerPanel.add(loadPanel);				
		loadPanel.setLayout(new FlowLayout());
		loadPanel.add(new JLabel("파일선택"));		
		printFileList();
	}
	
	public void printFileList() {
		
		
		for(File info : new File("save/").listFiles()) {
			
				JLabel fileName = new JLabel(info.getName());
				loadPanel.add(fileName);
				fileName.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
							loadName = info.getName();													
							JOptionPane.showMessageDialog(null, "선택되었습니다.");								
						}					
				});
		
			
		}
	}
		
	
	@Override
	public void confirmOperation() {
		// TODO Auto-generated method stub						
		try {
			if(com.loadFile(loadName)){
				JOptionPane.showMessageDialog(null, "불러오기 성공");	
			};
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		this.doDefaultCloseAction();
		layeredPane.remove(tab);		
	}

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub		
		this.doDefaultCloseAction();
		layeredPane.remove(tab);			
	}
}