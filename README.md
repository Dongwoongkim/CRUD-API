# CRUD API
# 개요

- 명칭 : Spring Boot 게시판 API 구현
- 개발 언어 : Java 11
- 개발 환경 : Spring Boot 2.7.12
- 데이터베이스 : H2
- 형상관리 툴 : GitHub
- 메인 기능
    - 1. 게시판 - CRUD 기능, 페이징, 검색
    - 2. 댓글 - CRUD 기능
    - 3. 회원 - Security 회원가입, 로그인, 회원정보 수정, 유효성 및 중복 검사 ( 추후 Security 학습 이후 진행 예정)
- 서브 기능
  - 

---

# **API 설계 - CRUD**

게시판 기능을 제공하는 RESTful API 해당 API는 HTTP 프로토콜을 사용하며, JSON 형식의 데이터를 주고받습니다.

| 기능 | 메소드 | URL | RETURN TYPE |
| --- | --- | --- | --- |
| 단건 조회 요청 | GET | /boards/{id} | BoardResponseDto = {title, content, writer} |
| 전체 조회 요청 ( 페이징  적용 ) | GET | /boards?page={id} | BoardResponseDto_list = { data : {title, content, writer}, … } |
| 전체 조회 요청 | GET | /boards | BoardResponseDto_list = { data : {title, content, writer}, … } |
| 게시물 작성 | POST | /boards | BoardResponseDto |
| 게시물 수정 | PUT | /boards/{id} | BoardResponseDto |
| 게시물 삭제 | DELETE | /boards/{id} | “삭제완료” |

| 기능 | 메소드 | URL | RETURN TYPE |
| --- | --- | --- | --- |
| 회원 등록 | POST | /member/save | MemberResponseDto = {id, username, password, nickname, email, role} |
| 회원 수정 | PATCH | /member/patch/{id} | MemberResponseDto = {id, username, password, nickname, email, role} |

---

# Pre-setting

1. build.gradle (dependencies)

```
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-web'
compileOnly 'org.projectlombok:lombok'
runtimeOnly 'com.h2database:h2'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

1. application.yml (H2 Database - dataSource)

```
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/board
    username: sa
    password:
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create
      default_batch_fetch_size: 1000
    properties:
      hibernate:
      show_sql: true
      format_sql: true

logging.level:
  org.hibernate.SQL: debug
```

---
---
