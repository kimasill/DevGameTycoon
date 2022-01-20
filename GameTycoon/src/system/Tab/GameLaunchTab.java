package system.Tab;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import system.Struct.Company;
import system.Struct.DevGame;
import system.UI.GameUI;


public class GameLaunchTab extends Tab implements GameUI{
    private JPanel panel = new JPanel();
    private DevGame game;
    public GameLaunchTab(Company com, int n){
        super("∞‘¿” √‚Ω√≈«",com);
        game = com.getProject(n);
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        
        
      
        
        panel.add(new JLabel(""+game.getTitle()));
        panel.add(new JLabel(""+game.getGame().getInterest()));
        centerPanel.add(panel);
    }

    @Override
    public void confirmOperation() {
        // TODO Auto-generated method stub
    	com.launchGame(game);
    	
    	layeredPane.remove(tab);
		this.dispose();
		
    }

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub
		
	}
}