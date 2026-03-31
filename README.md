# DevCom Tycoon — 게임 회사 경영 시뮬레이션

<p align="center">
  <a href="https://github.com/kimasill/DevGameTycoon"><img alt="GitHub Repo" src="https://img.shields.io/badge/GitHub-DevGameTycoon-181717?style=for-the-badge&logo=github&logoColor=white" /></a>
  <img alt="Java" src="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/>
  <img alt="Swing" src="https://img.shields.io/badge/UI-Java%20Swing-2F6DB3?style=for-the-badge" />
</p>

<p align="center">
  <a href="https://kimasill.github.io/projects/devcomtycoon.html" title="DevCom Tycoon 프로젝트 페이지" target="_blank" rel="noopener noreferrer">
    <img src="https://kimasill.github.io/images/DevComTycoon/Title.png" alt="DevCom Tycoon 타이틀" width="640" />
  </a>
</p>

링크 · [프로젝트 페이지](https://kimasill.github.io/projects/devcomtycoon.html) · [웹 포트폴리오](https://kimasill.github.io/)

> Java Swing 기반 게임 회사 경영 시뮬레이션 **DevCom Tycoon** 소스 레포지토리입니다. 상용 엔진 없이 시간 루프·경제·UI 동기화를 묶은 구현 요약을 아래에 개조식으로 정리합니다.

### Overview

| 항목 | 내용 |
| --- | --- |
| 장르 | 게임 회사 경영 시뮬레이션(타이쿤) |
| 엔진·스택 | Java · Java Swing |
| 기간·규모 | 핵심 2인 · 약 2개월(2020) · 팀명 게발게발 |

### Role

- 핵심 프로그래밍·시스템 설계 공동(핵심 2인), 본인 담당: 메인 시간 루프·일시정지, 직원 이동·애니메이션, 개발·출시·매출 경제, UI 갱신 동기화, 세이브/로드 등 타이쿤 전반

---

## Visual: Game Architecture

<p align="center">
  <img src="https://kimasill.github.io/images/DevComTycoon/dev%EA%B2%8C%EC%9E%84%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98.png" alt="DevCom Tycoon 게임 아키텍처" width="820" />
</p>

---

## Core Implementation

### 1. Game Loop – 시간 흐름을 한 스레드에 고정

- **목적**: 실시간에 가까운 시간 흐름·백그라운드 진행을 멀티스레드로 구현
- **문제**: 매출·개발 진행 타이밍 불일치 시 날짜 정지·이중 정산 등 버그
- **대응**: `GameBoard` 스레드에서 시간 진행, 메뉴 표시 중 또는 `POPUP_LAYER`에 컴포넌트 있을 때 시뮬 정지, 24시간 루프와 팝업 조건으로 하루·정산 버그 원인 단일화

> 📄 [`GameTycoon/src/system/GameBoard.java`](https://github.com/kimasill/DevGameTycoon/blob/main/GameTycoon/src/system/GameBoard.java#L125-L188) — `run()` 게임 루프

```java
@Override
public void run() {
    int hour = 0;
    try {
        while (true) {
            sleep(sleepTime);

            if (isRun && !menu.isVisible()
                    && layeredPane.getComponentCountInLayer(JLayeredPane.POPUP_LAYER) == 0) {

                if (hour == 12) {
                    // 점심 — 책상에 있는 직원 체력 회복
                    for (Developer dev : com.getDevList()) {
                        if (!dev.isMoving && dev.isWorkable()
                            && dev.x == dev.deskPos.x && dev.getHealth() < dev.getMaxHealth())
                            dev.rest(2);
                    }
                }
                if (++hour == 24) {
                    com.setTime(1);
                    com.sellGame();

                    for (Developer dev : com.getDevList()) {
                        if (!dev.isMoving && dev.x != dev.deskPos.x)
                            dev.goDesk();
                        if (dev.getHealth() == 0)
                            dev.goHome();
                    }

                    if (com.getTime() % 30 == 0) {
                        if (com.adjustment())
                            delinquencyStack++;
                        else
                            delinquencyStack = 0;
                    }

                    if (com.getProjectCount() != 0) {
                        com.progressProject();
                    }
                    if (delinquencyStack == 3) {
                        JOptionPane.showMessageDialog(null, "파산입니다.");
                        System.exit(0);
                    }
                    hour = 0;
                }
            }
        }
    } catch (InterruptedException e) { ... }
}
```

---

### 2. Developer Movement – 이동·애니메이션 동기

- **문제**: 엔진 없이 스프라이트 이동 시 경로·프레임 타이밍 불일치 시 깜빡임·순간이동 체감
- **대응**: 전용 스레드에서 `frameNum`·좌표 갱신 후 `repaint`, `Thread.sleep(20)` + 4틱마다 스프라이트 프레임 전환으로 설명 가능한 구조

<p align="center">
  <img src="https://kimasill.github.io/images/DevComTycoon/TitleImg.png" alt="DevCom Tycoon 개발 화면 스크린샷" width="640" />
</p>

> 📄 [`GameTycoon/src/system/Struct/Developer.java`](https://github.com/kimasill/DevGameTycoon/blob/main/GameTycoon/src/system/Struct/Developer.java#L177-L228) — `run()` 이동·스프라이트 애니메이션

```java
@Override
public void run() {
    int frmSpeed = 0;
    while (isMoving) {
        try {
            Thread.sleep(20);               // 프레임 속도
            if (++frmSpeed == 4) {          // 4틱마다 스프라이트 프레임 교체
                if (++this.frameNum == 4)
                    this.frameNum = 0;
                frmSpeed = 0;
            }
            x += 4 * direction;             // 방향 이동

            // 엘리베이터 탑승 (층 이동)
            if (x < 0 && direction < 0) {
                direction = 1; x = 4;
                y = dst_y;
            // 퇴근 (화면 밖)
            } else if (x > FRAME_WIDTH) {
                this.isMoving = false;
                Thread.sleep(2000);
                this.health = maxHealth;
                x = 800 - 64; y = 2600;
            // 목적지 도착
            } else if (x == dst_x && y == dst_y) {
                Thread.sleep(2000);
                this.dst_x = -1; this.dst_y = -1;
                this.frameNum = 0;
                this.isMoving = false;
                this.rest(1);
            }

            draw.repaint();
        } catch (InterruptedException e) { ... }
    }
}
```

---

### 3. Economy Loop – 돈의 출처를 한 곳에 모으기

- **대응**: `DevGame`에 진행도, `Company`에 시간·자금·프로젝트 슬롯을 수렴시켜 경제 관련 버그 추적 범위를 회사·프로젝트 객체로 한정

> 📄 [`GameTycoon/src/system/Struct/DevGame.java`](https://github.com/kimasill/DevGameTycoon/blob/main/GameTycoon/src/system/Struct/DevGame.java#L34-L57) — `addProgress()` 팀 능력 합산 후 진행도 가산

```java
public void addProgress() {
    if (this.progress >= 100)
        return;
    int devSpeed = 10000 / game.getInterest();
    int totalDevAbility = 0;
    for (Developer dev : team) {
        if (dev == null)
            break;
        if (dev.work())
            totalDevAbility += dev.getAbility();
    }
    if (totalDevAbility == 0)
        return;
    devSpeed *= (1 + (totalDevAbility) / 100);
    this.progress += (float) devSpeed / 100;
}
```

> 📄 [`GameTycoon/src/system/Struct/Company.java`](https://github.com/kimasill/DevGameTycoon/blob/main/GameTycoon/src/system/Struct/Company.java#L42-L53) — 초기 자금·규칙·프로젝트 슬롯

```java
public Company(String companyName, Developer defaultDev, int time, Rule rule) {
    this.companyName = companyName;
    this.projectCount = 0;
    this.devList.add(defaultDev);
    this.money = 200;
    this.popularity = 1;
    this.rule = rule != null ? rule : new Rule();
    this.time += time;
    gameUpdater.signal(this);
    setItemSellList();
    setDevSellList();
}
```

---

### 4. Simulation & UI – UI 갱신·시뮬레이션 진행 분리

- **문제**: UI 스레드와 시뮬 스레드 혼선 시 팝업 중에도 시간 진행, 설정 창 열린 사이 하루 경과 등 체감과 불일치
- **대응**: `GameUpdater`에서 `wait`/`notify`로 UI 갱신 시점 분리, 상태바·진행바 동기화, 세이브는 `ObjectOutputStream` 등으로 직렬화, 갱신 경로 단일화로 팝업 버그 재현성 향상

> 📄 [`GameTycoon/src/system/UI/GameUpdater.java`](https://github.com/kimasill/DevGameTycoon/blob/main/GameTycoon/src/system/UI/GameUpdater.java#L23-L50) — `run()` + `signal()`

```java
@Override
public void run() {
    while (true) {
        try {
            synchronized (this) {
                this.wait();
                statusBarUpdate();
                progressBarUpdate();
            }
        } catch (InterruptedException e) { ... }
    }
}

public void signal(Object object) {
    this.object = object;
    synchronized (this) {
        this.notify();
    }
}
```

> 📄 [`GameTycoon/src/system/Struct/Company.java`](https://github.com/kimasill/DevGameTycoon/blob/main/GameTycoon/src/system/Struct/Company.java#L72-L120) — Save/Load (`ObjectOutputStream` / `ObjectInputStream`)

```java
public boolean saveFile(String fileName) {
    try {
        new File("save").mkdirs();
        FileOutputStream fo = new FileOutputStream("save/" + fileName + ".save");
        ObjectOutputStream save = new ObjectOutputStream(fo);
        save.writeObject(this);
        save.close();
    } catch (IOException e) { return false; }
    return true;
}

public boolean loadFile(String fileName) throws ClassNotFoundException {
    FileInputStream fo = null;
    Company com;
    try {
        fo = new FileInputStream("save/" + fileName);
        ObjectInputStream load = new ObjectInputStream(fo);
        com = (Company) load.readObject();
        companyName = com.companyName;
        money = com.money;
        // ... 전체 상태 복원
        gameUpdater.signal(this);
    } catch (IOException e) { return false; }
    return true;
}
```

---

## Visual: Class Diagram & In-Game Screen

<p align="center">
  <img src="https://kimasill.github.io/images/DevComTycoon/dev%ED%81%B4%EB%9E%98%EC%8A%A4%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png" alt="DevCom Tycoon 클래스 다이어그램" width="820" />
</p>

<p align="center">
  <img src="https://kimasill.github.io/images/DevComTycoon/%EA%B0%9C%EB%B0%9C%ED%99%94%EB%A9%B4.png" alt="DevCom Tycoon 개발 화면 스크린샷" width="640" />
</p>

---

## Problem Solving

- 팝업 표시 중 시뮬 진행: `POPUP_LAYER`·메뉴 가시성을 루프 조건에 넣어 차단
- 엔진 미사용: 규칙을 코드로 고정해 동작 예측 가능성 확보

---

## Result

- 상용 엔진 없이 싱글플레이 경제 루프를 완결한 구현 사례

---

## 실행 캡처 (Demo)

**Intro** — 게임 시작 시 첫 인트로 화면

<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150803998-8fdb33db-4641-473c-a303-7822a035cef1.png" width="400px"></p>

**InGame** — 사무실, 상단에 날짜·자금 표시

<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150941216-518819eb-6969-4fee-abc8-d81d4ecd9bfb.png" width="400px"></p>

상점에서 아이템 구매·배치, 직원 고용

<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150941720-a5b8cc3c-d1c8-4ec6-9965-f0abac3d353c.png" width="400px"></p>

게임 출시 — 옵션 선택

<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150941754-48239e4b-6805-4c0f-8f31-288a082b17f6.png" width="400px"></p>

게임 중반 진행 화면

<p align="center"><img src="https://user-images.githubusercontent.com/80378085/150943337-20824107-3bcd-46d0-9b4c-e9ac0f270846.png" width="400px"></p>

파산 / 엔딩

| 파산 | 엔딩 |
|:---:|:---:|
| ![파산](https://user-images.githubusercontent.com/80378085/150944920-70539d1f-3354-4654-a5a5-0743c36cd130.png) | ![엔딩](https://user-images.githubusercontent.com/80378085/150945003-58fc2868-4ef2-44c8-912b-db41408cf29f.png) |

---

## 사용 이미지 목록

35 Images

![이미지 목록](https://user-images.githubusercontent.com/80378085/150935543-e8dd3525-1656-435c-b189-68961a1ac7b1.png)

![추가 이미지](https://user-images.githubusercontent.com/80378085/150950345-9677d99b-8535-4cb8-8c30-c98b47de16ff.png)

---

## Getting Started

1. Java 8 이상이 설치된 환경을 준비합니다.
2. IDE에서 `GameTycoon/` 프로젝트를 연 뒤 `GameBoard.java`의 `main`을 실행합니다.
3. 또는 `javac`로 컴파일한 뒤 `java system.GameBoard`로 실행합니다.
