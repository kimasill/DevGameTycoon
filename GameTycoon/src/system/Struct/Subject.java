package system.Struct;

public enum Subject{
    ��Ÿ��	(100),
    ����		(150),
    ����		(250),
    ���̹���ũ	(500),
    ������ũ	(200),
    ����		(100),
    ����		(300),
    �߼��ô�	(700),
    �̷�		(800),
    ����		(300),
    �׼�		(200),
    ����		(100),
    ����		(300),
    ��		(400),
    �౸		(500),
    ����		(100),
    �ִϸ��̼�	(100),
    ĳ�־�	(300),
    ����		(50);

    private int cost;
    
    Subject (int cost) {
        this.cost = cost;
    }
    
    public int getCost(){
    	return this.cost;
    }
}