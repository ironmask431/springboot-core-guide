package kevin.springboot.core.guide.batch.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kevin.springboot.core.guide.dto.ProductRequest;
import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.entity.User;
import kevin.springboot.core.guide.service.FeignApiTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign-api/product")
@PreAuthorize("isAuthenticated()")
@Tag(name = "BATCH API", description = "Feign Api TEST")
@Slf4j
public class FeignApiTestController {

    private final FeignApiTestService feignApiTestService;

    @Operation(summary = "전체 상품 조회 feign api call")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProduct(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(feignApiTestService.findAllProduct());
    }

    @Operation(summary = "상품 등록 feign api call")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@AuthenticationPrincipal User user, @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(feignApiTestService.createProduct(request));
    }
}
