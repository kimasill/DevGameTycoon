package system.Struct;

import java.util.Random;

public enum FirstNameSet {
	��,��,��,��,��,��,��,��,��,��,��,��,
	����,���,�����ں���,�ΰ�,����,���ӽ�,���̵�,�̵�,��ī��,������,
	�̼�,��Ʃ,���ڹ�,�˷���,������,�ٴϿ�,���̵�,�ø���,
	ī��,���ٽ���,�Ƹ���,���,����,���Ǿ�;
	
	public static String getName() {
		Random random = new Random();
		FirstNameSet list[] = FirstNameSet.values();
		
		return list[random.nextInt(list.length)].toString();
	}
}
