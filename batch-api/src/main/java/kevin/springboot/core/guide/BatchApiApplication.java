package kevin.springboot.core.guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 멀티모듈 Application 파일 생성 시 유의 사항.
 * 의존 하려는 모듈 (root모듈 또는 별도로 만든 core모듈) 의 application 파일과
 * 패키지명이 동일해야 한다. 그래야 참고모듈의 spring security, controllerAdvice, Swagger 등 각종 설정이 멀티모듈 에도 똑같이 적용된다.
 * 모듈들의 기본 상위 패키지명은 ROOT모듈과 모두 동일하게 가고, 모듈별로 하위 패키지명을 따로 지정하면 될 듯하다.
 */

@SpringBootApplication
public class BatchApiApplication {
    public static void main(String[] args) {
//        root 모듈의 application.yml 을 참조하기 위한 설정.
//        root 모듈의 application.yml 에는 모든 모듈이 공통으로 사용하는 설정값을 입력해놓으면 하위 모듈에 설정값이 중복되는 현상을 줄일 수 있다.
//        각 하위 모듈별로 존재하는 application-(모듈명).yml 에는 모듈별 고유 설정을 입력해놓는다.
//        *주의 : 하위 모듈에 application.yml 파일이 있으면, root모듈의 application.yml을 참조하지 않고,
//        가까이에 있는 현재 모듈의 application.yml을 참조하므로, 하위 모듈의 yml 파일은 이름을 다르게 해놔야 한다.
//        ex) application-api.yml
        System.setProperty("spring.config.name", "application,application-batch");
        SpringApplication.run(BatchApiApplication.class, args);
    }
}