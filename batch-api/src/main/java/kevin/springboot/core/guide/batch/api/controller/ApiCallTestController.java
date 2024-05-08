package kevin.springboot.core.guide.batch.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.entity.User;
import kevin.springboot.core.guide.service.ApiCallTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/api-call")
@Tag(name = "BATCH API", description = "API CALL TEST")
@Slf4j
public class ApiCallTestController {

    private final ApiCallTestService apiCallTestService;

    @Operation(summary = "전체 상품 조회 api call")
    @GetMapping
    @PreAuthorize("isAuthenticated()") // 메소드별로도 적용 가능
    public ResponseEntity<List<ProductResponse>> findAllProduct(@AuthenticationPrincipal User user) {
        log.info(user.toString());
        return ResponseEntity.status(HttpStatus.OK)
                             .body(apiCallTestService.findAllProduct());
    }
}
