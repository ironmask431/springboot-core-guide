package kevin.springboot.core.guide.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kevin.springboot.core.guide.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Swagger 문서에서 모듈별로 별도로 api가 나오게 하기 위해서 컨트롤러의 패키지명을 모듈별로 각각 다르게 할 필요가 있음.
 * 모듈1 : kevin.springboot.core.guide.api.controller
 * 모듈2 : kevin.springboot.core.guide.batch.api.controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Tag(name = "TEST API", description = "api 테스트 api")
public class TestController {

    @Operation(summary = "테스트 api")
    @GetMapping
    public String test(){
        return "api test success.";
    }

    @Operation(summary = "dto 타입으로 매핑받고 리턴하기 api")
    @GetMapping("/request-dto")
    public MemberDto getRequestDto(@Validated MemberDto dto) {
        return dto;
    }
}
