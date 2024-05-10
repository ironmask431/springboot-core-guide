package kevin.springboot.core.guide.api.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnviromentTest {
    private final Environment env;
    //jar 구동 시 VM option 설정 :  -Dspring.profiles.active=prd
    @PostConstruct // 의존성 주입이 완료된 후 메소드가 실행되도록 함.
    public void init() {
        //yml파일의 url 값 조회
        log.info("EnviromentTest url = {}", env.getProperty("url"));

        //현재 활성화된 환경변수 확인
        log.info("EnviromentTest env.getActiveProfiles() = {}", env.getActiveProfiles());

        if (env.acceptsProfiles(Profiles.of("dev"))) {
            log.info("this env on dev.");
        }
        if (env.acceptsProfiles(Profiles.of("prd"))) {
            log.info("this env on prd.");
        }
    }
}
