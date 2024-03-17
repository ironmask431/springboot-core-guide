package kevin.springboot.core.guide.repository;

import kevin.springboot.core.guide.dto.ProductResponse;
import kevin.springboot.core.guide.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsById(Long id);

    void deleteById(Long id);

    @Query(
        "SELECT " +
            "NEW kevin.springboot.core.guide.dto.ProductResponse(p.id, p.name, p.price, p.stock, p.isActive, p.createdAt, p.updatedAt) " +
        "FROM " +
            "Product p " +
        "WHERE " +
            "p.name LIKE CONCAT('%',:name,'%') "
    )
    Page<ProductResponse> findByNameLike(@Param("name")String name, Pageable pageable);

    long countByName(String name);

    long removeByName(String name); //삭제한 row수 리턴

    List<Product> findTop10ByName(String name);

    Product findByIdIsNot(Long id);

    Product findByisActiveIsTrue();

    Product findByisActiveIsFalse();

    Product findByIdAndName(Long id, String name);

    Product findByIdOrName(Long id, String name);

    List<Product> findByPriceGreaterThan(Long price);

    List<Product> findByPriceGreaterThanEqual(Long price);

    List<Product> findByPriceLessThan(Long price);

    List<Product> findByPriceLessThanEqual(Long price);

    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    List<Product> findByNameLike(String name);

    List<Product> findByNameContains(String name);

    Page<Product> findByNameContains(String name, Pageable pageable);

    List<Product> findByNameStartsWith(String name);

    List<Product> findByNameEndsWith(String name);

    List<Product> findByNameOrderById(String name);

    List<Product> findByNameOrderByIdDesc(String name);

}
