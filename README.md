# Boot3-REST-JWT  
[REST JWT 강의자료 다운 ](docs%2FREST%20JWT_202401.pdf)  

[Setting Projects]  
1. Spring Boot  
 - Spring MVC  
2. Spring Data JPA  
3. Spring Validation  
4. Spring HATEOAS  
 - Hypermedia as the Engine of Application State (HATEOAS) is a constraint of the REST application architecture that distinguishes it from other network application architectures.    
 - Hypertext Application Language (HAL)  
5.Springdoc-OpenApi  
 - https://springdoc.org/  
6. Spring Security  
 - JWT(json web token)  

[Setting Project]  
Java 버전 17로 시작  
스프링 부트 프로젝트 생성 ( https://start.spring.io/ )  
추가할 의존성 ( dependency )  
• Web  
• Data JPA  
• HATEOAS  ( https://spring.io/projects/spring-hateoas/ )  
• Validation  
• H2 / MariaDB Client  
• Lombok  
• Devtools  
• Configuration Processo  

[응답코드 정의]  
ResponseEntity를 사용하는 이유  
 - 응답 코드, 헤더, 본문을 모두 저장 해주는 편리한 API  
 
https://restfulapi.net/http-status-201-created/  

[프로파일 정의]  
프로파일 가이드 : https://docs.spring.io/spring-boot/docs/1.2.0.M1/reference/html/boot-features-profiles.html  

[jpa 옵션]  
application.properties 의 spring.jpa.hibernate.ddl-auto=create-drop
```
spring.jpa.hibernate.ddl-auto=create|create-drop|update|validate|none  
 - create  
  JPA가 DB와 상호작용할 때 기존에 있던 스키마(테이블)을 삭제하고 새로 만드는 것을 뜻한다.  
 - create-drop
  JPA 종료 시점에 기존에 있었던 테이블을 삭제합니다.
 - update
  기존 스키마는 유지하고, 새로운 것만 추가하고, 기존의 데이터도 유지한다. 
  변경된 부분만 반영함, 주로 개발 할 때 적합하다.
 - validate
  엔티티와 테이블이 정상 매핑 되어 있는지를 검증합니다.
```  
[jpa 페이징]  
Spring Data JPA의 페이징과 정렬  
 - Spring Data JPA에서는 쿼리 메소드에 페이징과 정렬 기능을 제공하는 2가지 클래스를 제공  
 - org.springframework.data.domain.Sort : 정렬 기능  
 - org.springframework.data.domain.Pageable : 페이징 기능  
 - Pageable 인터페이스는 Paging 하기 위한 파라미터들을 담은 PageRequest 같은 객체에 접근하기 위한 역  
할을 합니다. 이 매개변수를 통해 Paging 하기 위한 정보를 담은 객체가 들어오게 되고 이것을 통해 자동적  
으로 Paging에 필요한 데이터를 처리하여 반환하게 되는 것입니다.  
 - page : 검색을 원하는 페이지 번호를 나타냅니다.  
 - size : 한 페이지의 보여 줄 게시물 개수를 나타냅니다.  
 - sort : 정렬 방식을 나타냅니다.  

[에러처리]  
 - ProblemDetail을 이용한 에러처리 

[Spring Security]  
security 아키텍처  
![security.jpeg](docs%2Fsecurity.jpeg)  

@EnableMethodSecurity  
 - @PreAuthorize는 해당 메서드가 호출되기 이전에 해당 메서드를 호출할 권한이 있는지를 확인한다.  
 - hasAuthority('ROLE_ADMIN') 는 ' ROLE_ ' 접두사가 자동으로 추가 되기 때문에 hasRole('ADMIN') 과 유사합니다 .  
 - @PreAuthorize는 관리자 권한(ROLE_ADMIN)이 없는 username으로 로그인하면 관리자 권한이 없다는 403에러를 발생시킨다.  

[OAuth 2.0]  
OAuth는 인증을 위한 오픈 스탠더드 프로토콜로,사용자가 Facebook이나 트위터 같은 인터넷 서비스의 기능을 다른 애플리케이션(데스크톱, 웹, 모바일 등)에서도 사용할 수 있게 한 것.  

OAuth2.0 용어들  
- Resource Owner : 사용자(User)  
- Resource Server : 자원을 호스팅 하는 REST API 서버  
- Authorization Server : 인증서버 (API 서버와 같을 수도 있음), Access Token 발행  
- Client : 써드파티 어플리케이션 (서비스)  

OAuth 4가지 인증 방식  
- Authorization Code Grant – 권한 부여 승인 코드 방식  
- Implicit Grant – 암묵적 승인 방식  
- Resource Owner Password Credentials Grant – 자원 소유자 자격증명 승인 방식  
- Client Credentials Grant – 클라이언트 자격증명 승인 방식  

다양한 Token 지원
1. Access Token  
   : OAuth 2.0은 기본적으로 Bearer 토큰, 즉 암호화 하지 않은 그냥 토큰을 주고 받는 것으로 인증을 한다.  
   기본적으로 HTTPS 를 사용하기 때문에 토큰을 안전하게 주고 받는 것은 HTTPS의 암호화에 의존한다.   
   또한 복잡한 signature 등을 생성할 필요가 없기 때문에 curl이 API를 호출 할 때 간단하게 Header 에 아래와 같이 한 줄을 같이
   보내어 API를 테스트해 볼 수 있다.  
   Authorization: Bearer

2. Refresh Token    
   : 클라이언트가 같은 access token을 오래 사용하면 해킹에 노출될 위험이 높아지므로 OAuth 2.0에서는 refresh
   token 이라는 개념을 도입했다.  
   즉, 인증 토큰(access token)의 만료 기간을 가능한 짧게 하고 만료가 되면 refresh token으로 access token을 새로 갱신하는 방법이다.  

OAuth2.0 프로세스  
  
![oauth.png](docs%2Foauth.png)  

OAuth와 JWT의 관계  
- OAuth는 토큰을 발급하고 인증하는 오픈 스탠다드 프로토콜이며 프레임워크이다.  

- JWT는 이러한 프레임워크에서 발생하는 산출물로 볼 수 있다.  

- OAuth2.0는 하나의 플랫폼의 권한(아무 의미 없는 무작위 문자열 토큰)으로 다양한 플랫폼에서 권한을 행사할 수 있게 해줌으로써 리소스 접근이 가능하게 하는데 목적을 두고 있다.  

- JWT는 Cookie, Session을 대신하여 의미 있는 문자열 토큰으로써 권한을 행사할 수 있는 토큰의 한 형식이다. (로그인세션이나 주고받는 값이 유효한지 검증할 때 주로 쓰인다.)

[JWT]  
JWT(Json Web Token) 구조  
https://javadoc.io/doc/io.jsonwebtoken/jjwt-api/latest/index.html  

![jwt token.png](docs%2Fjwt%20token.png)  
Header : 토큰의 타입과 암호화 알고리즘으로 구성되어 있다.

Payload : 토큰에 담을 정보가 들어 있으며 Payload에 담은 정보의 한 ’조각’을 클레임(claim)이라고 부르고,  
이는 name/value 의 한쌍으로 이루어져 있습니다.   
클레임의 종류는 등록된(registered)클레임, 공개(public)클레임, 비공개(private)클레임이 존재합니다.  

Signature: 서명은 [헤더 base64 + 페이로드 base64 + SECRET_KEY ] 를 사용하여 JWT 백엔드에서 발행됩니다  
요청이 올 때마다 서명이 체크 됩니다.  
header 또는 payload의 정보가 클라이언트에 의해 변경된 경우 서명이 무효화 됩니다.  


admin token  
{ "email":"admin@aa.com", "password":"pwd1" }  
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhYS5jb20iLCJpYXQiOjE3MDYxNzMzMDksImV4cCI6MTcwNjE3NjkwOX0.BvdKV6rA_Fe8r5MQKYvGBhx3HvhXvTWdrFYBzyeo3TM
  

user token  
{ "email":"user@aa.com","password":"pwd2"}  
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGFhLmNvbSIsImlhdCI6MTcwNjE3MzM0OCwiZXhwIjoxNzA2MTc2OTQ4fQ.43bsUswbCwl7yGr38brtVBxWFRbW_Ya9vaXpuRxZZvk

  