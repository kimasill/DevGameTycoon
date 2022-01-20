package system.UI;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

import system.Struct.Company;
import system.Tab.GameLaunchTab;

public class ProgressBar implements GameUI{
	
	public ProgressBar(Company com) {
		progressPane.setOpaque(false);
		progressPane.setBounds(10, 60, 166, 75);
		progressPane.setLayout(new GridLayout(6, 1, 0, 0));
		layeredPane.setLayer(progressPane, 100);
		layeredPane.add(progressPane);
		
		progressPane.add(project1);
		progressPane.add(projectProgress1);
		
		project1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(projectProgress1.getValue() >= 100)
					layeredPane.add(new GameLaunchTab(com,0),JLayeredPane.POPUP_LAYER);
			}
		});
		progressPane.add(project2);
		progressPane.add(projectProgress2);
		project2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(projectProgress2.getValue() >= 100)
					layeredPane.add(new GameLaunchTab(com,1),JLayeredPane.POPUP_LAYER);
			}
		});
		progressPane.add(project3);
		progressPane.add(projectProgress3);
		project3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(projectProgress3.getValue() >= 100)
					layeredPane.add(new GameLaunchTab(com,2),JLayeredPane.POPUP_LAYER);
			}
		});
		
		for(Component c: progressPane.getComponents()) {
				c.setVisible(false);
		}
	}
}
