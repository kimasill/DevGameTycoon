package system.Tab;

import java.util.Random;

public enum EventScript {
	
	
	���൵_�̽�(
			"���� �ۼ��ߴ��� �𸣰����� ������ �־�̳׿�. �ڵ带 �����ϴ°� �������?",
			"�����ۼ��� �ڵ�� ����ϳ׿�. ���Ӱ��߿� ���� ������ �� �Ͱ��ƿ�!",
			"�������� ���� �ڵ尡 ū������ �����׾��Ф�  ��𼭺��� �ٽ� �������� ���� ������ �ʴ±���",
			0,0,10),
	���൵_�̽�2(
			"���ӿ� ���ο� �õ��� �غ����մϴ�. â���������� ������ ��������ʾƿ�",
			"�������� �۾��̾����ϴ�.",
			"�õ��� ��������.. ����� ���������� ���ϳ׿�",
			0,0,10);
	/*
	���൵_����_����(
			"���۽����� ������ �߻��߽��ϴ�. �����ڵ��� ������ �߾��� ����",
			"�۾��Ѱ� ���ư����. ������ ��Ȱȭ�սô�.",
			"���ེ���Ե� ������ �Ǿ��ֽ��ϴ�.",
			0,0,0),
	���൵_����_����(
			"���۽����� ������ �߻��߽��ϴ�. �����ڵ��� ������ �߾��� ����",
			"�۾��Ѱ� ���ư����. ������ ��Ȱȭ�սô�.",
			"���ེ���Ե� ������ �Ǿ��ֽ��ϴ�.",
			0,0,0);
	,
	���൵_������("��ǻ�Ͱ� ����",
			"��ǻ�Ͱ� ���峪�� ������ �Ҽ��� ���� ",
			"�̰� ����� �̽��� �ƴϿ�����? ó������ �����ؾ������� �𸣰ڳ׿�",
			),
	��̵�_���ο�õ�("���ο� �õ�",
			"�̰� �����",
			"��Ÿ���׿�",
			);
			*/
	
	
	
	
	

	
	private String mainScript;
	private String successScript;
	private String failScript;
	
	private int money;		//ȸ�������̺�Ʈ
	private int interest;	//�������ΰ����� ��̵�
	private int progress;	//�������ΰ������൵
	
	EventScript(String mainScript, String successScript, String failScript, int money, int interest, int progress) 
	{
		this.mainScript = mainScript;
		this.successScript = successScript;
		this.failScript = failScript;
		this.money = money;
		this.interest = interest;
		this.progress = progress;
	}
	public static EventScript getEventScript() {
		EventScript es[] = EventScript.values();
		int len = es.length;
		Random rand = new Random();
		return es[rand.nextInt(len)];
	}
	public String getMainScript() {
		return this.mainScript;
	}
	public String getSuccessScript() {
		return this.successScript;
	}
	public String getFailScript() {
		return this.failScript;
	}
	public int getMoney() {
		return this.money;
	}
	public int getProgress() {
		return this.progress;		
	}
	public int getInterest() {
		return this.interest;
	}
}
