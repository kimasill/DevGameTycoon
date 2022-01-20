package system.Tab;

import java.util.Random;

public enum EventScript {
	
	
	진행도_이슈(
			"누가 작성했는진 모르겠지만 문제가 있어보이네요. 코드를 수정하는게 좋을까요?",
			"새로작성한 코드는 깔끔하네요. 게임개발에 많은 도움이 될 것같아요!",
			"수정하지 않은 코드가 큰문제를 일으켰어요ㅠㅠ  어디서부터 다시 시작할지 감도 잡히지 않는군요",
			0,0,10),
	진행도_이슈2(
			"게임에 새로운 시도를 해볼까합니다. 창의적이지만 성공은 보장되지않아요",
			"성공적인 작업이었습니다.",
			"시도는 좋았지만.. 결과는 성공적이지 못하네요",
			0,0,10);
	/*
	진행도_정전_성공(
			"갑작스러운 정전이 발생했습니다. 개발자들이 저장을 했었길 빌죠",
			"작업한게 날아갔어요. 저장을 생활화합시다.",
			"다행스럽게도 저장이 되어있습니다.",
			0,0,0),
	진행도_정전_실패(
			"갑작스러운 정전이 발생했습니다. 개발자들이 저장을 했었길 빌죠",
			"작업한게 날아갔어요. 저장을 생활화합시다.",
			"다행스럽게도 저장이 되어있습니다.",
			0,0,0);
	,
	진행도_기자재("컴퓨터가 고장",
			"컴퓨터가 고장나면 개발을 할수가 없죠 ",
			"이거 사소한 이슈가 아니였군요? 처음부터 개발해야할지도 모르겠네요",
			),
	흥미도_새로운시도("새로운 시도",
			"이게 뭘까요",
			"안타깝네요",
			);
			*/
	
	
	
	
	

	
	private String mainScript;
	private String successScript;
	private String failScript;
	
	private int money;		//회사재정이벤트
	private int interest;	//개발중인게임의 흥미도
	private int progress;	//개발중인게임진행도
	
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
