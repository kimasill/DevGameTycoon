package system.Struct;

public enum ItemType {
	SOFA("sofa"),FOODCOURT("");
	
	final private String name;
	
	ItemType(String name) {
		this.name = name;
	}
	
	String getAdress(String name){
		return name;		
	}
}
