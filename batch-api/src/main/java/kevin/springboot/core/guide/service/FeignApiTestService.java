package kevin.springboot.core.guide.service;

import kevin.springboot.core.guide.dto.ProductRequest;
import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.feign.api.ApiProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignApiTestService {

    private final ApiProxyService apiProxyService;

    public List<ProductResponse> findAllProduct() {
        return apiProxyService.findAllProduct();
    }

    public ProductResponse createProduct(ProductRequest request) {
        return apiProxyService.createProduct(request);
    }
}
