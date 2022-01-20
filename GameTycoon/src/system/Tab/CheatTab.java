package system.Tab;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import system.GameBoard;
import system.Struct.Company;
import system.UI.GameUI;


public class CheatTab extends Tab implements GameUI{
    private JTextArea log;
    private JTextField input;

    public CheatTab(Company com){
        super("TEST CONSOLE",com);
        centerPanel.setLayout(new BorderLayout());
        log = new JTextArea("");
        log.setEditable(false);
        log.setAutoscrolls(true);
        input = new JTextField();

        input.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	String command = input.getText();
            	log.setText(log.getText()+"\n"+command);
            	
            	input.setText("");
                
                String[] argv = command.split(" ");
                int val;
                
                if(argv.length != 2) {
                	log.setText(log.getText()+"\nError : 인자는 반드시 2개입니다.");
                	return;
                }
                
                try {
            		val = Integer.parseInt(argv[1]);
            	}catch(NumberFormatException e1) {
            		log.setText(log.getText()+"\nError : 두번째 인자의 값이 정수가 아닙니다.");
            		return;
            	}
                if(argv[0].compareTo("money") == 0) {
                	com.setMoney(val);
                }else if(argv[0].compareTo("time") == 0){
                	com.setTime(val);
                }
                
//                else if(argv[0].compareTo("speed") == 0){
//                	gameBoad.setSleepTime(val);
//                }else if(argv[0].compareTo("game") == 0){
//                	if(val <= 0)	
//                		gameBoad.gamePause();
//                	else	
//                		timer.gameResume();
//                }
                
                else{
                	log.setText(log.getText()+"\nError : 존재하지 않는 명령어");
                }
            }
        });
        centerPanel.add(log,BorderLayout.CENTER);
        centerPanel.add(input,BorderLayout.SOUTH);
    }

    @Override
    public void confirmOperation() {
        // TODO Auto-generated method stub

        this.doDefaultCloseAction();

    }

	@Override
	public void cancelOperation() {
		// TODO Auto-generated method stub
		
	}
}
