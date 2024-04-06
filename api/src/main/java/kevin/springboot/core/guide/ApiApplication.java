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
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}