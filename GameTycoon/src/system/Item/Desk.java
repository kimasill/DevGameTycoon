package system.Item;

import java.io.Serializable;

import system.Struct.Developer;

public class Desk extends Item implements Serializable {
	private Developer owner=null;
	private String asd = "Ok";
	private String imgAdress = "Desk_64,128";
	private String imgTabAdress = "Desk_64,64";
	private int cost = 50;
	public Desk(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public String getAdress() {
		return this.imgAdress;
	}
	public String getTabAdress() {
		return this.imgTabAdress;
	}
	public void setOwner(Developer Dev) {
		this.owner = Dev;
	}
	public Developer getOwner() {
		return this.owner;
	}
	public void getMoney() {
		
	}
	public int getCost() {
		return this.cost;
	}
	public String asd() {
		return asd;
	}
}
