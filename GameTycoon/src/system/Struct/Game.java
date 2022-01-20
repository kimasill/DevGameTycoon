package system.Struct;

import java.io.Serializable;

public class Game implements Serializable{
	private String title;
	private Subject subject;
	private Genre genre;
	private int interest; 
	private int price;
	private int launchTime;
	private int cumulativeSales = 0;
	
	public Game(String title, Subject subject, Genre genre) {
		this.title = title;
		this.subject = subject;
		this.genre = genre;
		this.interest = Rule.getInterest(subject, genre);
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setLaunchTime(int time) {
		this.launchTime = time;
	}
	public void setPrice() {
		this.price = 1;
	}
	
	public int getInterest() {
		return this.interest;
	}
	public int getPrice() {
		return this.price;
	}
	public int getCumulativeSales() {
		return cumulativeSales;
	}
	public void setCumulativeSales(int cumulativeSales) {
		this.cumulativeSales = cumulativeSales;
	}
	public int getLaunchTime() {
		return this.launchTime;
	}
	
	
}
