FROM openjdk:17-jdk
# Docker 이미지를 생성할 때 기본으로 사용할 베이스 이미지를 설정하는 명령어이다.
# 이 경우, openjdk:17-jdk 이미지를 사용하여 JDK(Java Development Kit) 17이 설치된 환경에서 애플리케이션을 실행할 수 있도록 한다.
# OpenJDK는 자바 애플리케이션을 실행하는 데 필요한 런타임 환경을 제공한다.
WORKDIR /spring-boot
# Docker 컨테이너 내에서 작업 디렉토리를 /spring-boot로 설정한다. 이후의 모든 명령어는 이 디렉토리 내에서 실행된다.
# 이 디렉토리가 존재하지 않으면 자동으로 생성된다.
COPY ../api/build/libs/*SNAPSHOT.jar app.jar
# 호스트 시스템의 build/libs/ 경로에 있는 *SNAPSHOT.jar 파일을 Docker 컨테이너의 /spring-boot/ 디렉토리에 app.jar 파일로 복사한다.
# build/libs/에 *SNAPSHOT.jar 파일이 존재해야 한다.
ENTRYPOINT ["java", "-jar", "/spring-boot/app.jar"]
# Docker 컨테이너가 시작되면 java -jar /spring-boot/app.jar 명령어가 실행된다.
# 이 명령어는 Spring Boot 애플리케이션을 실행시킨다.

# 이 Dockerfile은 OpenJDK 17을 기반으로 한 자바 애플리케이션을 Docker 컨테이너에서 실행하기 위한 설정이다.
# 애플리케이션의 빌드 결과물인 SNAPSHOT.jar 파일을 컨테이너로 복사하고, 컨테이너가 시작될 때 해당 파일을 자바 명령어로 실행하는 방식이다.
