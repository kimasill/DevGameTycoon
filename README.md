# DevGameTycoon  
게임회사타이쿤을 주제로 한 2인 팀 프로젝트 입니다.  
<br/>
Using Language/IDE <br/>
<img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/>
<img alt="Eclipse" src ="https://img.shields.io/badge/Eclipse-2C2255.svg?&style=for-the-badge&logo=Eclipse IDE&logoColor=white"/>
<br/>
![image](https://user-images.githubusercontent.com/80378085/150740871-3094c803-637d-40ef-a434-5bfc95d901ce.png)
<br/>
- GUI 구현에 Java Swing 사용. 

## 1. 게임 방식:  
<br/>

플레이어는 게임회사 CEO가 되어, 게임회사를 운영합니다. 일정 시간 동안 자본금을 사용해서 게임을 개발하는데, 게임은 여러 가지 요소의 조합과 개발할 직원 선택, 개발 도중 이벤트 대응 등의 유저의 선택으로 재미있는 게임을 만들어낼 수 있습니다. 만들어진 게임을 판매하여 높은 수익을 올릴 수 있고, 여러 이벤트 로그 선택지에 대응하여 매 회차 다양한 업적등을 이루어 낼 수 있습니다.

<br/>

## 2. 구현 내용과 방식:      

<br/>

### 2.1. 쓰레드를 통한 시간흐름 
타이쿤 게임의 특성상 시간 흐름에따라 게임이 진행됩니다. Java는 타 게임 제작 툴과 달리 게임 제작에 특화되어 있지 않기 때문에 이를 직접 구현해야 합니다. 따라서 게임 전체를 관통하는 유일한 쓰레드 클래스를 하나 두고, 년/월/일/시 로 나누어 시간 흐름을 구현하였습니다.
<br/>   

### 2.1.1. 유저의 작업 중 게임의 흐름 정지
유저가 메뉴 토글 버튼을 활성화 하는 경우로 탭이 띄워져있는 상태임을 확인하면 게임의 흐름이 멈추게 만들었습니다.<br/>

### 2.1.2. 게임내 캐릭터들의 움직임
캐릭터들은 각기 알고리즘을 가지고 이동합니다. 이동방식은 도트캐릭터를 스프라이트 형태로 나눈 후, 쓰레드 카운트를  이미지 개수에 맞게 나누어 각 번호에 해당하는 이미지를 영사 합니다. 
<br/>

### 2.1.2.1. 캐릭터 알고리즘
캐릭터들의 알고리즘은 각 캐릭터의 스탯, 회사 내 배치된 사물에 영향을 받습니다. 출근 시 엘리베이터 방향으로 향   하여, 자신의 책상을 찾아가고, 퇴근시 출구로 나갑니다. 디테일한 알고리즘은 시간 제약상 생략 하였습니다.   <br/>
<br/>

### 2.2. 아이템/게임 출시
저희 타이쿤 게임에서 가장 중요한것은 오브젝트 입니다. 오브젝트는 직원의 능력에 영향을 미치고, 이는 회사의 성장과 직결됩니다. 또 회사는 게임을 제작하고, 출시하여 자금을 축적합니다. 이를 위해서는 다양한 게임 장르, 특성 등을 조합하고, 제작에 들어가는 인력, 자금 등을 책정해야 합니다.
<br/>

### 2.2.1. 아이템 구매
아이템은 메뉴 탭을열어 상점창을 연 후, 돈을 지불하고 구매하면 인벤토리로 아이템이 옮겨집니다. 이를 위해 탭 인터페이스를 구현하고 이를 상속하는 상점탭을 만들었습니다. 이 탭에는 무작위로 존재하는 아이템이 등록되고, 유저가 구매하면 인벤토리 역할을 하는 ArrayList로 옮겨집니다. 만약 건물을 구매한다면, 구매 버튼을 누르는 즉시 현재층 위에 한층씩 사무실이 추가 됩니다.
<br/>

### 2.2.2. 아이템 배치
인벤토리에 있는 아이템을 배치 할 수 있습니다. 인벤토리 또한 탭 인터페이스를 상속받아 구현되었으며, 현재 존재하는 아이템들을 보여줍니다. 유저는 인벤토리에서 아이템을 클릭하여, 드래그 드랍할 수 있습니다. 드랍시 아이템은 해당 위치에 고정됩니다. 이것을 구현하기 위해, 맵을 grid 형태로 분할 하고, 위치를 모두 매핑해놓았습니다. 유저가 일정 위치에서 아이템을 놓는 순간, 매핑된 위치에 아이템을 띄워주는 방식을 사용하였습니다.
<br/>

### 2.2.3. 게임 출시
유저는 게임을 출시하기 위해서 다양한 파라미터를 설정 할 수 있습니다. 이에 따른 다양한 효과를 미리 정해 놓기 위해서 별도의 클래스를 만들어 정해진 알고리즘에 따라 가중치를 계산하게 하였습니다. 게임을 출시하기로 하면 해당 게임들은 출시 리스트에 들어가게 되고, 게임 시간에 따라 진행도가 나타납니다. 해당 게임에 투입된 인력들은 출근하여 정해진 알고리즘대로 움직입니다. 게임에 들어가는 인력과 자금이 많을수록, 진행도는 빠르게 차오릅니다.
<br/>
<br/>

### 2.3. 이미지 제작
게임에 들어가는 모든 이미지는
[aseprite](https://www.aseprite.org/) 
툴을 이용하여 제작하였습니다. 이미지 목록은 
[여기](#5-사용-이미지-목록) 
에서 확인 가능합니다.
<br/>
<br/>
### 2.4. 게임 화면
게임 화면은 전체적으로 스크롤 패널에, 사무실 한칸으로 구성된 패널을 추가하는 방식으로 구성하였습니다. 사용자가 사무실 한 층을 구매할때마다, 미리 매핑된 좌표에 패널을 한칸씩 추가합니다.
<br/>

### 2.4.1 화면 이동
층이 많아지면 사용자가 아래층 부터 위층까지 자유롭게 확인이 가능해야 합니다. 이를 위해 마우스 포인터가 뷰 바깥쪽 에 다가가면, 위 또는 아래로 뷰를 이동시킬 수 있도록 구현하였습니다.
<br/>
<br/>

### 2.5. 게임 진행
게임은 단순한 방식으로 진행됩니다. 회사를 증축하고, 게임을 판매하여 자금을 쌓습니다. 이것이 너무 간단하기 때문에, 이벤트 시스템을 추가했습니다. 게임이 진행됨에 따라, 미리 저장되어 있는 이벤트 로그가 화면에 나타나며, 사용자의 선택을 강요합니다. 이선택에 따라 게임의 진행방향이 달라집니다. 게임의 종료는 탑재되어있는 평판 알고리즘에 따라 회사의 평판이 일정 수준 이상으로 올라가게 되면, 엔딩을 보게 됩니다. 회사의 평판에는 자금, 출시된 게임의 인기, 사원 수 등이 영향을 미칠 수 있습니다.
<br/>
<br/>


## 3. 실행 캡처(Demo)

게임의 전반적인 실행 화면을 공개합니다.

**Intro**   
게임시작 시 첫 인트로 화면. 팀 로고를 비춰줍니다. 
<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150803998-8fdb33db-4641-473c-a303-7822a035cef1.png" width="400px"></p>
<br/>

**InGame**

게임을 시작하면 사무실의 모습을 보여줍니다. 상단에는 날짜, 회사자금을 표시합니다.
플레이어는 메뉴탭을 열어 행동할 수 있습니다.
<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150941216-518819eb-6969-4fee-abc8-d81d4ecd9bfb.png" width="400px"></p>
<br/>

상점에서 아이템을 사고, 배치합니다. 직원 고용탭에서는 직원고용을 할 수 있습니다. 직원마다 능력과, 필요한 자금이 다릅니다.
<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150941720-a5b8cc3c-d1c8-4ec6-9965-f0abac3d353c.png" width="400px"></p>
<br/>

직원과 자금이 있다면, 게임을 출시 합니다. 원하는 옵션을 선택할 수 있습니다.
<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150941754-48239e4b-6805-4c0f-8f31-288a082b17f6.png" width="400px"></p>
<br/>

일정 수준까지 게임을 진행하면, 회사가 어느 정도 성장할 수 있습니다. 아래는 게임 중반의 진행 화면입니다.
<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150943337-20824107-3bcd-46d0-9b4c-e9ac0f270846.png" width="400px"></p>
<br/>

잘못하면 파산할수도 있습니다. 게임을 잘 진행했다면, 엔딩을 볼 수 있습니다. 아래는 파산과 엔딩 화면입니다.
|파산|엔딩|
|:---:|:---:|
|![image](https://user-images.githubusercontent.com/80378085/150944920-70539d1f-3354-4654-a5a5-0743c36cd130.png)| ![image](https://user-images.githubusercontent.com/80378085/150945003-58fc2868-4ef2-44c8-912b-db41408cf29f.png)|

<br/>
<br/>

## 4. 중요 코드
프로젝트의 특징은 게임 흐름을 관장 하는 함수 입니다. Java Swing을 이용하여 GUI를 구현하였기 때문에, 정상적인 흐름으로 게임 진행을 위해서는 쓰레드 사용이 필수입니다. 다만 해당 방식으로는 프레임 레이트에 따른 싱크 기능을 구현하지 못하기 때문에, 싱글 게임 구현만 가능합니다. 
<br/>

**시간 흐름 부분**

```
public void run() {   
	int hour = 0;   
	try {   
		while (true) {   
			sleep(sleepTime);   
/*
*일정 시간동안 sleep하며 sleep 한 시간은 1시간이 됩니다. *24시간이 합쳐지면 게임시간으로 하루가 지나갑니다.
*/
			if (isRun && !menu.isVisible() && 		layeredPane.getComponentCountInLayer(JLayeredPane.POPUP_LAYER) == 0) 
			{   
			//메뉴가 나타나면 시간이 멈춤
/*
layeredPane의 자식 컴포넌트의 개수가 0일 때 시간을 체크합니다. 창이 열리면 시간이 *가지않습니다.
*/
			    if (hour == 12) {   
				//오전 오후   
			    }   
			    if (++hour == 24) {   
				// 하루가 지남   
				com.setTime(1);   
				com.sellGame();   
/*
*직원이 자리에 없을 때, 직원의 체력이 없을때를 체크합니다.
*체력이없다면 직원을 집으로 돌려보냅니다. 
/*
				for(Developer dev : com.getDevList()) {   
				    if(!dev.isMoving && dev.x != dev.deskPos.x)   
					dev.goDesk();   
				    if(dev.getHealth() == 0)   
					dev.goHome();   
				}                     
				if (com.getTime() % 30 == 0) {                               
				    if (com.adjustment())   
					delinquencyStack++;   
				    else   
					delinquencyStack = 0;   
				}   
				//프로젝트의 개수를 센다   
				if (com.getProjectCount() != 0) {   
				    com.progressProject();   
				}   
				if (delinquencyStack == 3) {  
				//파산 스택이 쌓이면 파산합니다. 
				    JOptionPane.showMessageDialog(null, "");   
				    System.exit(0);   
				}    
/*
*아이탬 구매 탭의 시간이 현재 시간이랑 14일 이상 차이 나지않으면 *아이탬 탭의 아이템이 바뀌지 않습니다. 개발자 탭도 동일.
*/
				if(com.getItemTabTime()+13<com.getTime()) 
				{   
					com.setItemSellList();   
					com.setItemTabTime(com.getTime());   
					
					if(com.getDevTabTime()+13<com.getTime()) 
					{   
						com.setDevSellList();   
						com.setDevTabTime(com.getTime());   
					}   
				}    
				hour = 0;   
			    }                     
			}   
		}   
	} 
	catch (InterruptedException e) 
	{   
		// TODO Auto-generated catch block   
		e.printStackTrace();   
	}   
}   
``` 
<br/>

**움직임**
```
public void run() {
	int frmSpeed = 0
	while(isMoving) { 
		try {
			Thread.sleep(20);//프레임속도 //프레임변경
			if(++frmSpeed == 4) { 
				if(++this.frameNum == 4) this.frameNum = 0
				frmSpeed = 0
			}
			//방향설정
			x += 4*direction
			//엘리베이터탑승
			if(x < 0 && direction < 0) {
				direction = 1 x=4
				y = dst_y //-64
				//집으로
			}
			else if(x > FRAME_WIDTH) { 
				this.isMoving = false 
				Thread.sleep(2000); 								this.health = maxHealth
				x = 800-64
				y = 2600

			//목적지도착
			}else if(x == dst_x && y == dst_y) { 
				Thread.sleep(2000); 			
				System.out.println("도착");
				this.dst_x =-1
				this.dst_y =-1
				this.frameNum = 0
				this.isMoving = false
				this.rest(1);
			}
			draw.repaint();//다시 그려준다.
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block 
		e.printStackTrace();
		}
	} 
System.out.println("이동종료");
}
```
<br/>

## 5. 사용 이미지 목록
35 Images
![image](https://user-images.githubusercontent.com/80378085/150935543-e8dd3525-1656-435c-b189-68961a1ac7b1.png)
<br/>

## 6. 프로젝트 세부사항
프로젝트에 대한 세부사항은 
[여기](https://kimasill.tistory.com/)
에서 확인 가능 합니다.

<br/>
![image](https://user-images.githubusercontent.com/80378085/150950345-9677d99b-8535-4cb8-8c30-c98b47de16ff.png)
