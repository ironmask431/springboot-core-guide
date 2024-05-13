package kevin.springboot.core.guide.feign.api;

import kevin.springboot.core.guide.dto.ProductRequest;
import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.feign.util.BearerTokenFormatter;
import kevin.springboot.core.guide.feign.util.SecurityAuthTokenGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiFeignClientProxyService implements ApiProxyService {

    private final ApiFeignClient apiFeignClient;

    @Override
    public List<ProductResponse> findAllProduct() {
        //SecurityContextHolder 에서 인증토큰 조회
        String authToken = SecurityAuthTokenGetter.getAuthToken();
        log.info("findAllProduct() - authToken = {}", authToken);
        final String token = BearerTokenFormatter.format(authToken);
        return apiFeignClient.findAllProduct(token).getResponse();
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        String authToken = SecurityAuthTokenGetter.getAuthToken();
        log.info("createProduct() - authToken = {}", authToken);
        final String token = BearerTokenFormatter.format(authToken);
        return apiFeignClient.createProduct(token, request).getResponse();
    }
}
