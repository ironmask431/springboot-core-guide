package kevin.springboot.core.guide.feign.api;

import kevin.springboot.core.guide.dto.BaseResponseDTO;
import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;
import java.util.List;

@FeignClient(value = "apiFeignClient", url = "localhost:8080", configuration = FeignConfig.class)
public interface ApiFeignClient {

    @GetMapping("/product")
    BaseResponseDTO<List<ProductResponse>> findAllProduct(URI baseUrl, @RequestHeader("Authorization") String token);

}
