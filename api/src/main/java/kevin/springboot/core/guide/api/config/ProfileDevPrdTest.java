//package kevin.springboot.core.guide.api.config;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//@Profile("dev | prd") // 환경변수 dev 또는 prd 일때 Bean 등록됨.
//public class ProfileDevPrdTest {
//
//    @Value("${url}")
//    private String url;
//
//    //jar 구동 시 VM option 설정 :  -Dspring.profiles.active=prd
//    @PostConstruct // 의존성 주입이 완료된 후 메소드가 실행되도록 함.
//    public void init() {
//        log.info("url : {}", url);
//    }
//
//    @Bean
//    public ProfileBean profileBean() {
//        return new ProfileBean("devPrd");
//    }
//}
