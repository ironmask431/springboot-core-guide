# 스프링부트 핵심가이드

### 버전정보
1. SpringBoot 3.2.1
2. java 17

### 구현한 것
1. Rest Api 
2. ExcptionHandler + CustomException 으로 예외 처리, 응답
3. Springdoc 으로 Swagger Api 문서화
4. Logback으로 콘솔 로그출력, 파일 로그저장 
5. 데이터베이스(mysql, mariaDB) 연동 
6. Jwt 를 이용해 로그인(토큰발급), API 인증 인가 프로세스 구축 
7. Controller, Service 테스트코드 기본 구성 
8. Page를 이용해 데이터를 page단위로 조회, 응답하기 
9. ResponseBodyAdvice 를 이용하여 response형태 통일 
10. Jpa Auditing 으로 도메인에 등록일자, 등록자 자동 입력 
11. Flyway로 DB migration 관리 설정 
12. AOP 기능활용 (API 요청시 - userActionLog 로깅 구축)
13. 멀티 모듈 구축 (root, api, batch-api 모듈 분리)

