# DevGameTycoon  
게임회사타이쿤을 주제로 한 프로젝트 입니다.  
<br/>
Using Language/IDE <br/>
<img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/>
<img alt="Eclipse" src ="https://img.shields.io/badge/Eclipse-2C2255.svg?&style=for-the-badge&logo=Eclipse IDE&logoColor=white"/>
<br/>
![image](https://user-images.githubusercontent.com/80378085/150740871-3094c803-637d-40ef-a434-5bfc95d901ce.png)
<br/>
- GUI 구현에 Java Swing 사용. 

## 1. 게임 방식:    
플레이어는 게임회사 CEO가 되어, 게임회사를 운영합니다. 일정 시간 동안 자본금을 사용해서 게임을 개발하는데, 게임은 여러 가지 요소의 조합과 개발할 직원 선택, 개발 도중 이벤트 대응 등의 유저의 선택으로 재미있는 게임을 만들어낼 수 있습니다. 만들어진 게임을 판매하여 높은 수익을 올릴 수 있고, 여러 이벤트 로그 선택지에 대응하여 매 회차 다양한 업적등을 이루어 낼 수 있습니다.

## 2. 구현 내용과 방식:      

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
아이템은 메뉴 탭을열어 상점창을 연 후, 돈을 지불하고 구매하면 인벤토리로 아이템이 옮겨집니다. 이를 위해 탭 인터페이스를 구현하고 이를 상속하는 상점탭을 만들었습니다. 이 탭에는 무작위로 존재하는 아이템이 등록되고, 유저가 구매하면 인벤토리 역할을 하는 ArrayList로 옮겨집니다.
<br/>

### 2.2.2. 아이템 배치
인벤토리에 있는 아이템을 배치 할 수 있습니다. 인벤토리 또한 탭 인터페이스를 상속받아 구현되었으며, 현재 존재하는 아이템들을 보여줍니다. 유저는 인벤토리에서 아이템을 클릭하여, 드래그 드랍할 수 있습니다. 드랍시 아이템은 해당 위치에 고정됩니다. 이것을 구현하기 위해, 맵을 grid 형태로 분할 하고, 위치를 모두 매핑해놓았습니다. 유저가 일정 위치에서 아이템을 놓는 순간, 매핑된 위치에 아이템을 띄워주는 방식을 사용하였습니다.
<br/>

### 2.2.3. 게임 출시
유저는 게임을 출시하기 위해서 다양한 파라미터를 설정 할 수 있습니다. 이에 따른 다양한 효과를 미리 정해 놓기 위해서 별도의 클래스를 만들어 정해진 알고리즘에 따라 가중치를 계산하게 하였습니다. 게임을 출시하기로 하면 해당 게임들은 출시 리스트에 들어가게 되고, 게임 시간에 따라 진행도가 나타납니다. 해당 게임에 투입된 인력들은 출근하여 정해진 알고리즘대로 움직입니다. 게임에 들어가는 인력과 자금이 많을수록, 진행도는 빠르게 차오릅니다.
<br/>
<br/>

### 3. 이미지 제작
게임에 들어가는 모든 이미지는
[aseprite](https://www.aseprite.org/) 
툴을 이용하여 제작하였습니다. 이미지 목록은 
[여기](#사용 이미지 목록) 
에서 확인 가능합니다.

5. 

- 중요 코드

**run함수** 
게임 흐름 관장   

```
	@Override   
	public void run() {   
		int hour = 0;   
		try {   
			while (true) {   
				sleep(sleepTime);   
				if (isRun && !menu.isVisible() && layeredPane.getComponentCountInLayer(JLayeredPane.POPUP_LAYER) == 0) {   
					//메뉴가 나타나면 시간이 멈춤
                    if (hour == 12) {   
                        //오전 오후   
                    }   
                    if (++hour == 24) {   
                        // 하루가 지남   
                        com.setTime(1);   
                        com.sellGame();   
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
                            JOptionPane.showMessageDialog(null, "");   
                            System.exit(0);   
                        }    
                        if(com.getItemTabTime()+13<com.getTime()) {   
	    					com.setItemSellList();   
	    					com.setItemTabTime(com.getTime());   
    					if(com.getDevTabTime()+13<com.getTime()) {   
        					com.setDevSellList();   
        					com.setDevTabTime(com.getTime());   
    					}   
    				}    
                        hour = 0;   
                    }                     
                }   
			}   
		} catch (InterruptedException e) {   
			// TODO Auto-generated catch block   
			e.printStackTrace();   
		}   
	}   
``` 
2. 

- 실행 캡처(Demo):   

**Intro**   
인트로 화면. 팀 로고를 비춰줍니다. 상단에는 날짜, 회사자금을 표시합니다.
플레이어는 메뉴탭을 열어 행동할 수 있습니다.
![image](https://user-images.githubusercontent.com/80378085/150803998-8fdb33db-4641-473c-a303-7822a035cef1.png)
<br/>

**InGame**
게임을 시작하면 사무실의 모습을 보여줍니다. 
![image](https://user-images.githubusercontent.com/80378085/150804194-7b7f4305-369e-4531-9dbe-b0229b72a543.png)

![image](https://user-images.githubusercontent.com/80378085/150803951-81381b4d-2658-4a11-b17e-de43000c439d.png)

**사용 이미지 목록**
- 
