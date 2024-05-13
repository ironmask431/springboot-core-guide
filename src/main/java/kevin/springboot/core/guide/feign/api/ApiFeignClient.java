package kevin.springboot.core.guide.feign.api;

import jakarta.validation.Valid;
import kevin.springboot.core.guide.dto.BaseResponseDTO;
import kevin.springboot.core.guide.dto.ProductRequest;
import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "apiFeignClient", url = "${feign.api.url}", configuration = FeignConfig.class)
public interface ApiFeignClient {

    @GetMapping("/product")
    BaseResponseDTO<List<ProductResponse>> findAllProduct(@RequestHeader("Authorization") String token);

    @PostMapping("/product")
    BaseResponseDTO<ProductResponse> createProduct(@RequestHeader("Authorization") String token,
                                                   @RequestBody @Valid ProductRequest request);
}
