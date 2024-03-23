package kevin.springboot.core.guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 멀티모듈 Application 파일 생성 시 유의 사항.
 * 참고하려는 모듈 (root모듈 또는 별도로 만든 core모듈) 의 application 파일과
 * 패키지명이 동일해야 한다. 그래야 멀티모듈이 참고 모듈의 bean들을 읽을 수 있고, 설정들을 공유 가능해진다.
 */

@SpringBootApplication
public class BatchApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchApiApplication.class, args);
    }
}