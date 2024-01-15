# 03.Final_Project (JAVA, JPA, Oracle) (다나가)

### <h2>⭐프로젝트 소개</h2>
수요가 높은 사이트 종류 중 하나인 전자 상거래 사이트 중 다나와를 벤치마킹한 사이트를 제작 하였습니다.

### <h2>⭐개발 기간</h2>
총 35일 (2023.10.12 ~ 2023.11.15)

### <h2>⭐담당 파트</h2>
| Member |
|----------|
| 회원API</br> 로그인</br> 카카오(로그인,주소)</br> 아이디 비밀번호 찾기</br> 이메일 인증</br> |


### <h2>⭐개발 환경</h2>

<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> 
<img src="https://img.shields.io/badge/Bootstrap 4-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> 
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> For distribute <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> For develop
<br>

### <h2>⭐상세</h2>
[어벤져스_발표자료.pdf](https://github.com/leedong617/leedong617/files/13663640/_.pdf)<br>
<https://github.com/leedong617/FinalProject/> github주소<br>
다나가 프로젝트 Docker EC2 RDS 배포 <http://3.37.149.223:5000/index>
<br>
### <h2>⭐개발 개요</h2>
<h2>❓왜 전자상거래 사이트를 프로젝트 주제로 삼았는지</h2>
<img width="487" alt="웹사이트 수요" src="https://github.com/leedong617/leedong617/assets/133841274/aea3a8c9-d294-488c-819c-e2e5b053e124">
<h3>▶️ 개발자들에게 있어 뗄레야 뗄 수 없는 GPT선생님께 물어본 결과 수요가 높은 사이트 유형 중 하나 이기도 하고 고민없이 배웠던 기술들을 잘 활용하여 보여 드릴 수 있는 주제 이기도 해서 정하게 되었습니다.</h3>
<br>

### <h2>⭐아키텍처</h2>
<img width="950" alt="아키텍처" src="https://github.com/leedong617/leedong617/assets/133841274/1c97c6b9-56b0-4919-b41d-af54dfa46a98">
<h3>▶️ 해당 프로젝트를 push하면 github actions를 통해 gradle로 jar 파일을 build하고 해당 jar파일로 image를 build해 docker hub에 push한 뒤 해당 웹 프로젝트를 docker를 통해 배포중인 ec2서버에서 docker image를 pull하고 실행 중 이던 docker container를 중지시키고 pull한 docker image로 다시 docker container를 실행시켜 자동 배포하는 CI / CD 환경을 구축하였습니다.</h3>
<h2>⭐ERD</h2>
<img width="1091" alt="ERD" src="https://github.com/leedong617/leedong617/assets/133841274/355a601b-7b3e-41ab-9fa1-a0571267f4cb">
<h2>⭐주요 기능</h2>
<img width="671" alt="스크린샷 2023-12-14 011833" src="https://github.com/leedong617/leedong617/assets/133841274/72517656-e00e-449d-9fb5-3a142f06a99a"><br>
<h3>❗Rest API </h3>
<img width="698" alt="rest api 코드" src="https://github.com/leedong617/leedong617/assets/133841274/209f9d11-2b0a-4b83-adc2-db52e2b70e98"><br>
<img width="374" alt="ajax요청" src="https://github.com/leedong617/leedong617/assets/133841274/910a453c-7253-4f6c-9397-92b692a04f9f"><br>
<img width="239" alt="REST API설명 1" src="https://github.com/leedong617/leedong617/assets/133841274/b82d75e5-576a-415e-9f40-195712dfcbfb"><br>
<h3>❗이메일 인증 </h3>
<h4>JavaMailSender API와 Google SMTP를 활용하여 이메일 인증 서비스를 구현하였습니다.</h4>
<img width="452" alt="이메일인증2" src="https://github.com/leedong617/leedong617/assets/133841274/669e47ea-e42b-43d4-bfc7-252bce2823b5"><br>
<img width="269" alt="이메일인증3" src="https://github.com/leedong617/leedong617/assets/133841274/7bfb3f02-76ab-44bb-b997-528f6ec9c31a"><br>
<img width="463" alt="이메일인증4" src="https://github.com/leedong617/leedong617/assets/133841274/0f5d4f28-298c-4c5d-94a9-af12e55d42b2"><br>
<img width="665" alt="이메일인증5" src="https://github.com/leedong617/leedong617/assets/133841274/ca2dffb7-6d4f-49e5-bf04-d74e4234ffb0"><br>
<h4>▶️ 전역 변수 number선언 및 전역 메소드 createNumber() 생성 <br>createNumber()는 number에 랜덤으로 5자리의 int를 할당합니다.
</h4>
<img width="479" alt="이메일인증6" src="https://github.com/leedong617/leedong617/assets/133841274/c4b5fe03-f3bf-4281-8d44-60d333ef8310"><br>
<h4>▶️ JoinCreateMail(String mail)<br>
createNumber()를 호출해 number를 할당하고 메일의 ‘수신자’와 ‘제목’ ‘내용’을 설정 후 반환합니다.
</h4>
<h4>▶️ JoinSendMail(String mail)<br>
JoinCreateMail(String mail)의 반환 받은 데이터를 발송하고 할당된 전역변수 number를 반환합니다. 
</h4>
<img width="328" alt="메일컨트롤러" src="https://github.com/leedong617/leedong617/assets/133841274/77fd4eda-c19c-423e-af9f-70aa8deabb7d"><br>
<h4>▶️ 이메일 인증 Rest API를 만들어 해당 URI로 요청이 들어오면 이메일 폼 데이터가 mail로 들어오고 해당 mail로 위 과정을 거치게 됩니다. 그 후 랜덤 숫자 5자리를 Stirng으로 변환 한 뒤 json 데이터로 반환합니다. </h4>
<img width="266" alt="이메일인증ajax" src="https://github.com/leedong617/leedong617/assets/133841274/c22beff0-f23c-4156-9b3b-96eb6ecdf7e3"><br>
<h3>❗카카오 로그인 </h3>
<img width="659" alt="카카오 로그인" src="https://github.com/leedong617/leedong617/assets/133841274/40aeb638-df1c-43ca-809d-d1772ccc4720"><br>
로그인<br>
![로그인](https://github.com/leedong617/leedong617/assets/133841274/a0d16215-ea3f-4a73-9a84-3c563f98efa9)<br>
카카오로그인<br>
![카카오로그인](https://github.com/leedong617/leedong617/assets/133841274/aecf4977-9630-45af-a797-d44f3d064843)<br>
카카오로그인2<br>
![카카오로그인2](https://github.com/leedong617/leedong617/assets/133841274/0e23f33b-5c99-4cd7-b5f8-17c48aee866c)<br>
카카오로그인3<br>
![카카오로그인3](https://github.com/leedong617/leedong617/assets/133841274/1c48966c-e636-4a74-847f-f13f92ca3565)<br>
회원가입<br>
![회원가입](https://github.com/leedong617/leedong617/assets/133841274/42dfb74b-3fd6-4af5-8852-16596bf04254)<br>
회원가입완료<br>
![회원가입완료](https://github.com/leedong617/leedong617/assets/133841274/ab121615-26d3-4264-9b10-7a0fcfe30a4d)<br>
마이페이지<br>
![마이페이지](https://github.com/leedong617/leedong617/assets/133841274/157cd114-84f3-4e0a-b024-56c58321c6e1)<br>
수정<br>
![수정](https://github.com/leedong617/leedong617/assets/133841274/bb8dbcca-f8e4-4e1f-9198-56a37bcc8efa)<br>
찾기<br>
![찾기](https://github.com/leedong617/leedong617/assets/133841274/a3e9f115-fb59-4f97-bc2c-1aa01818efea)<br>
탈퇴<br>
![탈퇴](https://github.com/leedong617/leedong617/assets/133841274/bed2d3c4-f4c3-4622-b537-5b18de485a07)<br>
