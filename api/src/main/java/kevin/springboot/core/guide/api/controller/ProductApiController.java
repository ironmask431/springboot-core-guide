package kevin.springboot.core.guide.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kevin.springboot.core.guide.annotation.Timer;
import kevin.springboot.core.guide.annotation.UserActionLog;
import kevin.springboot.core.guide.api.service.ProductService;
import kevin.springboot.core.guide.dto.ProductRequest;
import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "상품 API", description = "상품 API 입니다.")
@RequestMapping("/product")
@PreAuthorize("isAuthenticated()") // 이 컨트롤러의 api 들은 인증된 요청에게만 api 응답한다. (미인증시 401 unAuthorized)
@Slf4j
public class ProductApiController {

    private final ProductService productService;

    @Operation(summary = "모든 상품을 조회한다.")
    @GetMapping
    @PreAuthorize("isAuthenticated()") // 메소드별로도 적용 가능
    @UserActionLog // 메소드에 aop 적용
    @Timer
    public ResponseEntity<List<ProductResponse>> findAllProduct(@AuthenticationPrincipal User user) {
        // @AuthenticationPrincipal 를 통해 현재 인증된 사용자 정보를 가져 올 수 있다.
        // 매핑 받는 객체인 User가 UserDetails 를 implement로 구현한 객체일 경우 가능하다.
        log.info(user.toString());
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findAllProduct());
    }

    @Operation(summary = "단일 상품을 조회한다.")
    @GetMapping("/{id}")
    @UserActionLog
    @Timer
    public ResponseEntity<ProductResponse> findProductById(@AuthenticationPrincipal User user, @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findProductById(id));
    }

    @Operation(summary = "상품을 등록한다.")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @UserActionLog
    @Timer
    // hasRole('ADMIN')의 경우 authority에 "ROLE_권한명" 의 형태로 저장되어 있어야 매칭된다.
    public ResponseEntity<ProductResponse> createProduct(@AuthenticationPrincipal User user, @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(productService.createProduct(user, request));
    }

    @Operation(summary = "상품을 수정한다.")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @UserActionLog
    @Timer
    public ResponseEntity<Boolean> updateProduct(@PathVariable(name = "id") Long id,
                                                 @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.updateProduct(id, request));
    }

    @Operation(summary = "상품을 삭제한다.")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @UserActionLog
    @Timer
    public ResponseEntity<Boolean> deleteProduct(@AuthenticationPrincipal User user, @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.deleteProduct(id));
    }

    @Operation(summary = "상품들을 페이지 단위로 조회한다.")
    @GetMapping("/by-name")
    public ResponseEntity<Page<ProductResponse>> findPageAllProduct(@Parameter(description = "상품명") @RequestParam(name = "name", required = false) String name,
                                                                    @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findPageAllProduct(name, pageable));
    }

    @Operation(summary = "상품들을 페이지 단위로 조회한다.")
    @GetMapping("/by-name2")
    public ResponseEntity<Page<ProductResponse>> findPageAllProduct_2(@Parameter(description = "상품명") @RequestParam(name = "name", required = false) String name,
                                                                    @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findPageAllProduct_2(name, pageable));
    }

    @Operation(summary = "상품들을 페이지 단위로 조회한다.")
    @GetMapping("/by-name3")
    public ResponseEntity<Page<ProductResponse>> findPageAllProduct_3(@Parameter(description = "상품명") @RequestParam(name = "name", required = false) String name,
                                                                      @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findPageAllProduct_3(name, pageable));
    }


}
