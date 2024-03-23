package kevin.springboot.core.guide.service;

import kevin.springboot.core.guide.dto.ProductRequest;
import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.entity.Product;
import kevin.springboot.core.guide.entity.User;
import kevin.springboot.core.guide.exception.ProductNotFoundException;
import kevin.springboot.core.guide.repository.ProductRepository;
import kevin.springboot.core.guide.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    //전체 상품 조회
    public List<ProductResponse> findAllProduct() {
        return productRepository.findAll()
                                .stream()
                                .map(product -> ProductResponse.of(product))
                                .toList();
    }

    //특정 상품 조회
    @Transactional
    public ProductResponse findProductById(Long id) {
        Product product = findById(id);
        return ProductResponse.of(product);
    }

    //상품 입력
    @Transactional
    public ProductResponse createProduct(User user, ProductRequest request) {
        Product product = productRepository.save(request.toEntity());
        return ProductResponse.of(product);
    }

    //상품 수정
    @Transactional
    public boolean updateProduct(Long id, ProductRequest request) {
        Product product = findById(id);
        product.updateProduct(request.getName(), request.getPrice(), request.getStock(), request.getIsActive());
        return true;
    }

    //상품 삭제
    @Transactional
    public boolean deleteProduct(Long id) {
        findById(id);
        productRepository.deleteById(id);
        return true;
    }

    private Product findById(Long id) {
        Product product = productRepository.findById(id)
                                           .orElseThrow(() -> new ProductNotFoundException(id));

        return product;
    }

    //Page 처리 방식 1 : JPQL 사용하여 Page<ProductResponse> 로 조회 (조회하려는 Page만 잘라서 조회)
    public Page<ProductResponse> findPageAllProduct(String name, Pageable pageable) {
        Page<ProductResponse> productResponsePage = productRepository.findByNameLike(name, pageable);
        return productResponsePage;
    }

    //Page 처리 방식 2 : List<Product> 조회 후 Page<ProductResponse>로 변환 (전체 조회 후 Paging)
    public Page<ProductResponse> findPageAllProduct_2(String name, Pageable pageable) {
        List<Product> productList = productRepository.findByNameContains(name);
        List<ProductResponse> productResponseList = productList.stream()
                                                               .map(product -> ProductResponse.of(product))
                                                               .collect(Collectors.toList());
        return (Page<ProductResponse>) CommonUtil.convertListToPage(productResponseList, pageable);
    }

    //Page 처리 방식 3 : Page<Product> 로 조회 후 Page<ProductResponse> 로 변환 (조회하려는 Page만 잘라서 조회)
    public Page<ProductResponse> findPageAllProduct_3(String name, Pageable pageable) {
        Page<Product> productPage = productRepository.findByNameContains(name, pageable);

        List<ProductResponse> productResponseList = productPage.stream()
                                                               .map(product -> ProductResponse.of(product))
                                                               .collect(Collectors.toList());

        return new PageImpl<>(productResponseList, pageable, productPage.getTotalElements());
    }
}
