package kevin.springboot.core.guide.batch.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@PreAuthorize("isAuthenticated()") // 이 컨트롤러의 api 들은 인증된 사용자에게만 api 응답이 가능하다.
@Tag(name = "TEST API", description = "Batch-api 테스트 api")
public class TestController {

    @Operation(summary = "테스트 api")
    @GetMapping
    public String test(){
        return "batch-api test success.";
    }
}
