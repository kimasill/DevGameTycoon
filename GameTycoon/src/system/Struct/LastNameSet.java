package system.Struct;

import java.util.Random;

public enum LastNameSet {
	자르반,갑수,순자,람쥐,루이비통,돌쇠,점순,누렁이,
	치즈피자,콜라,팔광,용팔,성현,신우,석대,억두,두홍,대두,만식,성룡,병철,
	김치만두,샹크스,피카소,프란시스코,코너,아델만,도나휴,노박,미켈란젤로,잭슨,
	유선,마초,케인,Loden,흥민,베일,비니시우스,은돔벨레,라멜라,모우라,로셀로,
	레길론,알리,알더베이럴트,Dier,호이비에르,PlayStation5,GTX3080;
	
	public static String getName() {
		Random random = new Random();
		LastNameSet list[] = LastNameSet.values();
		
		return list[random.nextInt(list.length)].toString();
	}
}
