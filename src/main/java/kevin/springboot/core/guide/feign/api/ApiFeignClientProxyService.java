package kevin.springboot.core.guide.feign.api;

import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.feign.util.BaseUriCreator;
import kevin.springboot.core.guide.feign.util.BearerTokenFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class ApiFeignClientProxyService implements ApiProxyService {

    private final ApiFeignClient apiFeignClient;
    private final String prefixUrl;

    public ApiFeignClientProxyService(ApiFeignClient apiFeignClient, @Value("${feign.api.url}") String prefixUrl) {
        this.apiFeignClient = apiFeignClient;
        this.prefixUrl = prefixUrl;
    }

    @Override
    public List<ProductResponse> findAllProduct() {
        final URI uri = BaseUriCreator.create(prefixUrl);
        //SecurityContextHolder 에서 인증토큰 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authToken = (String)authentication.getCredentials();
        log.info("findAllProduct() - authToken = {}", authToken);
        final String token = BearerTokenFormatter.format(authToken);
        return apiFeignClient.findAllProduct(uri, token).getResponse();
    }
}
