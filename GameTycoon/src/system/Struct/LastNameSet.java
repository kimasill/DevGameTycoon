package system.Struct;

import java.util.Random;

public enum LastNameSet {
	�ڸ���,����,����,����,���̺���,����,����,������,
	ġ������,�ݶ�,�ȱ�,����,����,�ſ�,����,���,��ȫ,���,����,����,��ö,
	��ġ����,��ũ��,��ī��,�����ý���,�ڳ�,�Ƶ���,������,���,���̶�����,�轼,
	����,����,����,Loden,���,����,��Ͻÿ콺,��������,����,����,�μ���,
	�����,�˸�,�˴����̷�Ʈ,Dier,ȣ�̺񿡸�,PlayStation5,GTX3080;
	
	public static String getName() {
		Random random = new Random();
		LastNameSet list[] = LastNameSet.values();
		
		return list[random.nextInt(list.length)].toString();
	}
}
