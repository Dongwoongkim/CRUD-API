# CRUD API
1. **Domain**
    1. Board
        1. id(PK, IDENTITY 전략)
        2. title
        3. content
        4. writer
        5. createdDate ( null → save() , not null → merge() by Persistable )
2. **DTO**
    1. 요청 DTO
        1. BoardRequestDto : 저장
        2. BoardEditDto : 수정
    2. 응답 DTO
        1. BoardResponseDto
3. **Repository** : Spring Data JPA JpaRepository 사용
4. **Reponse** : request에 대한 response 객체
    1. success()
        1. success(T) : 응답 DTO 리턴
        2. sucess()
    2. failure() : 오류 코드, Failure 객체에 오류 메시지 담아 리턴
5. **Exception** : 저장, 수정, 조회에서 발생한 변환 예외
    1. BoardNotFoundException : 수정,삭제 조회 작업 중 조회 실패 예외
    2. WriteFailureException : 저장 실패 예외
    3. UpdateFailureException : 수정 실패 예외
6. **Advisor** : 5에서 발생한 예외 처리 후 Reponse로 변환 & 리턴하도록 하는 예외처리기
7. **Service** : Repository 접근
8. **web** : RequestMapping → Service 접근

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
