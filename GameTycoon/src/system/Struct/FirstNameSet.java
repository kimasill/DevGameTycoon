package system.Struct;

import java.util.Random;

public enum FirstNameSet {
	김,이,박,최,정,강,조,윤,장,추,한,우,
	라임,노아,엘리자베스,로건,마슨,제임스,에이든,이든,루카스,제이콥,
	미셸,매튜,벤자민,알랙스,윌리엄,다니엘,제이든,올리버,
	카터,세바스찬,아리아,루시,조이,소피아;
	
	public static String getName() {
		Random random = new Random();
		FirstNameSet list[] = FirstNameSet.values();
		
		return list[random.nextInt(list.length)].toString();
	}
}
