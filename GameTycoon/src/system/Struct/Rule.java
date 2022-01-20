package system.Struct;

import java.io.Serializable;
import java.util.Random;

public class Rule implements Serializable{
    protected static Subject subjects[];
    protected static Genre genres[];
    protected static int recipe[][];

    public Rule(){
        subjects = Subject.values();
        genres = Genre.values();
        recipe = new int[subjects.length][genres.length];
        Random s = new Random();
        
        
        for(int i = 0; i < subjects.length ; i++) {
        	for(int j = 0; j < genres.length ; j++) {
        		recipe[i][j] = (s.nextInt(90)+10);
        	}
        }
        
        
        
        
        
   }
    	
    //구현할것
	public static int getInterest(Subject subject, Genre genre) {
		// TODO Auto-generated method stub
		int x = 0;
		int y = 0;
		for(Subject s :subjects) {
			if(s == subject)
				break;
			x++;
		}
		for(Genre g :genres) {
			if(g == genre)
				break;
			y++;
		}
		
		return recipe[x][y];
	}
}