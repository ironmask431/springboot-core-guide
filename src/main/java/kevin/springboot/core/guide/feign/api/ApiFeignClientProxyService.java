package kevin.springboot.core.guide.feign.api;

import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.feign.util.BaseUriCreator;
import kevin.springboot.core.guide.feign.util.BearerTokenFormatter;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class ApiFeignClientProxyService implements ApiProxyService {

    private final ApiFeignClient apiFeignClient;
    private final String prefixUrl;
    private final String authToken;

    public ApiFeignClientProxyService(ApiFeignClient apiFeignClient) {
        this.apiFeignClient = apiFeignClient;
        this.prefixUrl = "localhost:8080";
        this.authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imlyb25tYXNrNDMxQGdtYWlsLmNvbSIsInJvbGUiOlt7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJNQU5BR0VSIn0seyJhdXRob3JpdHkiOiJVU0VSIn1dLCJleHAiOjE3MTUxNjQzNTYsImlzc3VlciI6ImtldmluIiwiaXNzdWVyQXQiOjE3MTUxNjA3NTYzNDB9.qH2NFmDdIOtNEMqC4MqcPWdzAg-yA9C7i9iLwJtKHPI";
    }

    @Override
    public List<ProductResponse> findAllProduct() {
        final URI uri = BaseUriCreator.create(prefixUrl);
        final String token = BearerTokenFormatter.format(authToken);
        return apiFeignClient.findAllProduct(uri, token).getResponse();
    }
}
