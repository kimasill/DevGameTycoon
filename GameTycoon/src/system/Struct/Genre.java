package system.Struct;

public enum Genre {
    FPS		(100),
    TPS		(100),
    RTS		(100),
    MOBA	(100),
    �ùķ��̼�	(100),
    �α׶���ũ	(100),
    ����		(100),
    ��Ʋ�ξ�	(100),
    ź��		(100),
    ����		(10),
    �����׼�	(100),
    �����̵�	(100),
    ������	(100),
    ����		(100),
    RPG		(100),
    ���̽�	(100),
    �������	(100);

	
	
    private int cost;
    Genre (int cost) {
        this.cost = cost;
    }
    public int getCost(){
    	return this.cost;
    }
}