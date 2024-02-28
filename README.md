## 03.Final_Project (JAVA / Spring Boot, JPA) (다나가)
  
## ⭐프로젝트 소개
수요가 높은 사이트 종류 중 하나인 전자 상거래 사이트 중 다나와를 벤치마킹한 사이트를 제작 하였습니다.

## ⭐개발 기간
총 35일 (2023.10.12 ~ 2023.11.15)

## ⭐담당 파트
| Member |
|----------|
| 회원API(회원가입,탈퇴,수정,로그인,찾기)</br> 카카오(로그인,주소)</br> 이메일 인증 API</br> 포인트 등급제 <br>|
### 🙋‍♂️ 멤버 구성
| [이동현 ](https://github.com/leedong617) <br> *(Member)*  | [최유강](https://github.com/choliea) <br> *(Product)* | [이승현](https://github.com/lsh96900410) <br> *(Cart)* | [임범준](https://github.com/beomjun10) <br> *(Cart)* |
| :------: |  :------: | :------: | :------: |
| - 회원API <br>(회원가입,탈퇴,수정,로그인,찾기)<br> -포인트 등급제 <br> - 카카오API <br> (지도/로그인) <br> - 이메일 인증 API |- 리스트 <br> - 상세페이지 <br> - 카테고리/옵션 검색 <br> - 관심상품/최근본상품 | - 장바구니 <br> - 옵션변경 <br> - 선택주문 | - 선택삭제 <br> - 비회원 장바구니 |
| [**권경록**](https://github.com/kkr95101) <br> *(Order)*  | [**박재찬**](https://github.com/ykmr0331) <br> *(Delivery)*  | [**김아론**](https://github.com/aronkim92) <br> *(Board)* | [**이현승**](https://github.com/roco-lee) <br> *(Admin, 팀장)* |
| - 주문처리 <br> - 비회원 주문 <br> 주문번호 이메일 발송  | - 주문목록 <br> 배송상태 조회/변경 <br> - 환불/취소 <br> - 비회원 주문조회   | - 게시판 <br> - 작성, 수정, 삭제 <br> - 댓글, 좋아요 <br> - 인기글 <br> - 에디터 기능 <br> - 페이징/검색 | - 사이트 통계 <br> - 엑셀 다운로드 <br> - 제품 등록/삭제 <br> - 주문상태 변경 <br> - 회원/게시글 삭제 |


## ⭐개발 환경

<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> 
<img src="https://img.shields.io/badge/Bootstrap 4-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> 
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"><br> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> For deployment <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> For development
<br>

## ⭐상세
[어벤져스_발표자료.pdf](https://github.com/leedong617/leedong617/files/13663640/_.pdf)<br>
<https://github.com/leedong617/FinalProject/> github주소<br>
<http://3.37.149.223:5000/index> 다나가 프로젝트 Docker EC2 RDS 배포
<br>

## ⭐아키텍처
<img width="950" alt="아키텍처" src="https://github.com/leedong617/leedong617/assets/133841274/bdf3b1d2-01b3-45fd-bc61-9b184feb0271">
<h3>>> 해당 프로젝트를 push하면 github actions를 통해 gradle로 jar 파일을 build하고 해당 jar파일로 docker image를 만들어 docker hub에 push한 뒤 해당 웹 프로젝트를 docker를 통해 배포중인 ec2서버에서 docker image를 pull하고 실행 중 이던 docker container를 중지시키고 pull한 docker image로 다시 docker container를 실행시켜 자동 배포하는 CI / CD 환경을 구축하였습니다.</h3>
<h2>⭐ERD</h2>
<img width="1091" alt="ERD" src="https://github.com/leedong617/leedong617/assets/133841274/355a601b-7b3e-41ab-9fa1-a0571267f4cb">
<h2>⭐주요 기능</h2>
<img width="671" alt="스크린샷 2023-12-14 011833" src="https://github.com/leedong617/leedong617/assets/133841274/72517656-e00e-449d-9fb5-3a142f06a99a"><br>
<h2>1️⃣ Rest API </h2>
<img width="683" alt="rest api 코드" src="https://github.com/leedong617/leedong617/assets/133841274/4aaff533-b4df-41ca-ac4b-d5d1f8434fc2"><br>
<img width="389" alt="ajax요청" src="https://github.com/leedong617/leedong617/assets/133841274/f80574bd-e37f-4131-ae12-76951a1bad4b"><br>
<img width="239" alt="REST API설명 1" src="https://github.com/leedong617/leedong617/assets/133841274/b82d75e5-576a-415e-9f40-195712dfcbfb"><br>
<h3>❗RestController를 사용하여 회원 Rest API를 구축 하였습니다.<br><br>
  >> 구축한 Rest API를 사용하여 회원가입 버튼을 누를시 AJAX로 요청한 URI의 메소드를 실행하고 JSON 데이터를 반환합니다. 반환받은 JSON 데이터를 이용하여 Tooltip 메시지를 띄우거나 회원가입 완료 페이지로 이동합니다.</h3>
<h2>2️⃣ 이메일 인증 </h2>
<h3>JavaMailSender API와 Google SMTP를 활용하여 이메일 인증 서비스를 구현하였습니다.</h3>
<img width="452" alt="이메일인증2" src="https://github.com/leedong617/leedong617/assets/133841274/669e47ea-e42b-43d4-bfc7-252bce2823b5"><br>
<img width="269" alt="이메일인증3" src="https://github.com/leedong617/leedong617/assets/133841274/7bfb3f02-76ab-44bb-b997-528f6ec9c31a"><br>
<img width="463" alt="이메일인증4" src="https://github.com/leedong617/leedong617/assets/133841274/0f5d4f28-298c-4c5d-94a9-af12e55d42b2"><br>
<img width="665" alt="이메일인증5" src="https://github.com/leedong617/leedong617/assets/133841274/ca2dffb7-6d4f-49e5-bf04-d74e4234ffb0"><br>
<h3>>> 전역 변수 number선언 및 전역 메소드 createNumber() 생성 <br>createNumber()는 number에 랜덤으로 5자리의 int를 할당합니다.
</h3>
<img width="479" alt="이메일인증6" src="https://github.com/leedong617/leedong617/assets/133841274/c4b5fe03-f3bf-4281-8d44-60d333ef8310"><br>
<h3>>> JoinCreateMail(String mail)<br><br>
createNumber()를 호출해 number를 할당하고 메일의 ‘수신자’와 ‘제목’ ‘내용’을 설정 후 반환합니다.
</h3>
<h3>>> JoinSendMail(String mail)<br><br>
JoinCreateMail(String mail)의 반환 받은 데이터를 발송하고 할당된 전역변수 number를 반환합니다. 
</h3>
<img width="328" alt="메일컨트롤러" src="https://github.com/leedong617/leedong617/assets/133841274/77fd4eda-c19c-423e-af9f-70aa8deabb7d"><br>
<h3>>> 이메일 인증 Rest API를 만들어 해당 URI로 요청이 들어오면 이메일 폼 데이터가 mail로 들어오고 해당 mail로 위 과정을 거치게 됩니다. 그 후 랜덤 숫자 5자리를 Stirng으로 변환 한 뒤 json 데이터로 반환합니다. </h3>
<img width="266" alt="이메일인증ajax" src="https://github.com/leedong617/leedong617/assets/133841274/c22beff0-f23c-4156-9b3b-96eb6ecdf7e3"><br>
<h2>3️⃣ 카카오 로그인 </h2>
<img width="628" alt="카카오 로그인2024-02-29 012852" src="https://github.com/leedong617/leedong617/assets/133841274/1d98a7e6-2216-493d-a5c4-2f6c7fabfa3f">
<h3>❗KakaoController와 KakaoService, KakaoProfile을 만들어
Kakao에서 보낸 code로 인증 토큰을 생성하고 생성된 토큰을 사용하여 KakaoProfile을 가져옵니다.
회원의 이메일에는 Unique설정되어있으며 KakaoProfile에서 가져온 이메일이 DB에 존재한다면 해당 이메일을 가진 회원으로 로그인이 진행되며 
존재하지 않는다면 MemberNotFoundException 발생하여 KakaoProfile을 사용하여 회원가입이 진행됩니다.
</h3>

## ⭐트러블슈팅
### 순환참조
개요 : Member REST API를 Swagger로 테스트 하던중 순환참조 에러가 발생하였다.
원인 : JPA를 사용해 Entity간 1:N 양방향 맵핑이 되있고 Entity를 그대로 반환할 경우 무한 참조가 발생하여 StackOverFlow가 난다.
해결 방법 : 많은 방법들이 있었지만 DTO를 만들어 반환하는 방법을 채택했다. DTO는 필요한 데이터만 담기 때문에 순환참조를 애초에 예방할 수 있다.
