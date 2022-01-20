package system.Struct;

import java.io.Serializable;

public class DevGame implements Serializable{
	private Game game;
	private Developer[] team;
	private float progress;		//0 ~ 100
	
	public DevGame(Game newGame, Developer[] team){
		this.game = newGame;
		progress = 0;
		this.team = team;
	}
	
	public String getTitle() {
		return this.game.getTitle();
	}
	public Game getGame() {
		return this.game;
	}
	public int getProgress() {
		return (int)this.progress;
	}
	public void setProgress(int num) {
		this.progress = this.progress - num;
		if(this.progress <0)
			this.progress = 0;
		
	}
	public Developer[] getTeam() {
		return this.team;
	}
	public void addProgress() {	//사용시 0으로 할것
		if(this.progress >= 100)
			return;
		int devSpeed = 10000 /game.getInterest();	//디폴트 속도  추후 초기 게임의 흥미도로 변경할것
		//System.out.println(game.getTitle());
		//System.out.println("interest " + game.getInterest());
		int totalDevAbility = 0;
		for(Developer dev : team) {
			if(dev == null)	
				break;
			if(dev.work()) {
				totalDevAbility += dev.getAbility();
			}
		}
		if(totalDevAbility == 0)
			return;
		devSpeed *= (1 + (totalDevAbility)/100);
		
		//System.out.println("progress " + progress);
		this.progress += (float)devSpeed/100;
		//System.out.println("progress " + progress);
		//System.out.println("devSpeed " + devSpeed);
		//System.out.println("totalDevAbility " + totalDevAbility);
	}
	
}
