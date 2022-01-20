package system.Struct;

public enum Subject{
    판타지	(100),
    마법		(150),
    전쟁		(250),
    사이버펑크	(500),
    스팀펑크	(200),
    동물		(100),
    좀비		(300),
    중세시대	(700),
    미래		(800),
    우주		(300),
    액션		(200),
    생존		(100),
    수집		(300),
    농구		(400),
    축구		(500),
    음악		(100),
    애니메이션	(100),
    캐주얼	(300),
    교육		(50);

    private int cost;
    
    Subject (int cost) {
        this.cost = cost;
    }
    
    public int getCost(){
    	return this.cost;
    }
}