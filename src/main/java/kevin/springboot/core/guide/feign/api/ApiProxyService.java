package kevin.springboot.core.guide.feign.api;

import kevin.springboot.core.guide.dto.ProductResponse;

import java.util.List;

public interface ApiProxyService {
    List<ProductResponse> findAllProduct();
}
