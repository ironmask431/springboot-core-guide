package kevin.springboot.core.guide.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "kevin.springboot.core.guide")
public class FeignConfig {

}
