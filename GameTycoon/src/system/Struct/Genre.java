package system.Struct;

public enum Genre {
    FPS		(100),
    TPS		(100),
    RTS		(100),
    MOBA	(100),
    시뮬레이션	(100),
    로그라이크	(100),
    격투		(100),
    배틀로얄	(100),
    탄막		(100),
    퍼즐		(10),
    대전액션	(100),
    아케이드	(100),
    스포츠	(100),
    공포		(100),
    RPG		(100),
    레이싱	(100),
    리듬게임	(100);

	
	
    private int cost;
    Genre (int cost) {
        this.cost = cost;
    }
    public int getCost(){
    	return this.cost;
    }
}