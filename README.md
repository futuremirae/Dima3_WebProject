
![logo](https://github.com/futuremirae/Dima_Project_1/assets/136614563/0f438981-8999-46d8-a80d-0bf20463d086) 
# 키워드 기반 바이어 매칭 시스템 "Nice to match You" 
한국 무역협회 디지털 마스터 과정 3기 2차 웹 프로젝트

## 프로젝트 소개 


## 🖥️ 프로젝트 개요 
- 개발 기간 : 2024.04 ~ 2024.05.03(1개월)
- 개발 멤버: 김미래, 주진명, 이태수, 홍서희, 장혜지
- 협업 기업: Nice D&B
- ⭐️ 담당 역할
  - 각 페이지 별 css 수정
  - 프론트 엔드 및 페이지별 배너 제작
  - 로그인 및 회원가입
  - 이메일 발송 기능
  - 마이페이지 (메일함, 찜기능, 내정보 확인)
  - 바이어 추천 페이지
  - 환율 계산기

## ⚙️ 개발 환경 
- 언어 : Java, HTML5, CSS3, JavaScript, Python
- 프레임 워크 : SpringBoot3.4, VsCode
- DB: Oracle19c
- API: Kotra/ CurrencyLayer/Fast API

## Flow Chart 
![flowchart](https://github.com/futuremirae/Dima_Project_1/assets/136614563/16ef6cca-45d0-4d7f-8cb1-bda598703934)
- (⭐️ 이 직접 구현한 기능입니다.)


## 메인 기능  

### 바이어 매칭 서비스
- 1) 파이썬으로 개발한 "추천 알고리즘"을  FastAPI 서버로 구현하여 스프링 서버와 연결
- 2) 사용자가 입력한 키워드를 바탕으로 바이어를 추천
- 3) 기업 추천 뿐만 아니라 해당기업의 상세정보확인, 찜기능, 이메일 발송 가능  
![바이어추천](https://github.com/futuremirae/Dima_Project_1/assets/136614563/91229b5f-1d1c-4a3e-9df2-dcb10f98ec09)

## 부가 기능 (⭐️ 이 직접 구현한 기능입니다.)

### ⭐️ 환율계산기 
- 1) 세계 각국의 환율을 실시간으로 계산할 수 있는 페이지 입니다.
- 2) currencyLayer의 api를 사용하여 각 나라의 환율 정보를 가지고 왔습니다.
 ![환율](https://github.com/futuremirae/Dima_Project_1/assets/136614563/c9ee3897-763f-4204-a5b9-45baa642ef9c)

### 해외 무역  뉴스 검색
- 자동차 관련 해외뉴스에 대해 검색할 수 있는 페이지입니다.
![무역뉴스](https://github.com/futuremirae/Dima_Project_1/assets/136614563/1ebfd432-a5af-41a2-8bd3-759a83e14495)

### 사기사례 검색
- 무역 사기사례에 대해 검색할 수 있는 페이지입니다.
![무역사기](https://github.com/futuremirae/Dima_Project_1/assets/136614563/c2f8d06c-d700-4e17-9203-5d2cca8f4c85)

### 무역 통계 
- 세계 각국의 무역 관련 통계에 대한 정보 제공 페이지
![무역통계](https://github.com/futuremirae/Dima_Project_1/assets/136614563/84df5993-17c3-4f33-b3e9-c735f20bb4f6)


## 기본 기능

### ⭐️ 마이페이지(관심 바이어)
- 사용자가 찜을 누른 바이어들을 관리할 수 있는 페이지입니다.
![관심바이어](https://github.com/futuremirae/Dima_Project_1/assets/136614563/4a8c7032-8c3e-4852-986d-21f285fb9f79)


### ⭐️ 메일함(보낸 메일 관리)
- 사용자가 보낸 메일을 확인하고 관리할 수 있는 페이지
- Java mailSender을 사용하여 메일 발송기능 구현
![apdlf](https://github.com/futuremirae/Dima_Project_1/assets/136614563/1db894ae-36ee-4343-b4a3-5ea05c58e3e5)

### ⭐️ 로그인 및 회원가입 
![로긍;ㄴ](https://github.com/futuremirae/Dima_Project_1/assets/136614563/388e25ab-e25c-42f5-a04e-d0ed60eedc5d)

### 공지사항 
- 관리자가 올린 공지사항을 확일 할 수 있습니다.
![공지사항](https://github.com/futuremirae/Dima_Project_1/assets/136614563/91fc95b5-dd49-4754-b595-84741c18049f)
