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
- 게임 방식:    
플레이어는 게임회사 CEO가 되어, 게임회사를 운영한다. 일정 시간 동안 자본금을 사용해서 게임을 개발하는데 게임은 여러 가지 
요소의 조합과 개발할 직원 선택, 개발 도중 이벤트 대응 등의 유저의 선택으로 재미있는 게임
을 만들어낼 수 있다. 만들어진 게임을 판매하여 높은 수익을 올린다.

- 구현 내용:   
Java Swing 으로 제작하였기 때문에 완전한 게임의 모습을 갖추지는 못하였습니다. 구현된내용은   
<br/>
1. 쓰레드를 통한 시간흐름   
타이쿤 게임의 특성상 시간 흐름에따라 게임이 진행됩니다. 따라서 게임 전체를 관통하는 유일한 쓰레드 클래스를 하나 두고, 년/월/일/시 로 나누어 시간 흐름을 구현하였습니다.   
1.1. 유저의 작업 중 게임의 흐름 정지   
유저가 메뉴 토글 버튼을 활성화 하는 경우로 탭이 띄워져있는 상태임을 확인하면 게임의 흐름이 멈추게 만들었다.
1.2.

- 중요 코드

1. **run함수** 게임 흐름 관장   

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

- 
