package com.hoang.shopapp.repository;

import com.hoang.shopapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByCategory_CategoryId(Integer categoryId);

    @Query(value = """
            select *\s
            from products p
            where (:minPrice IS NULL OR p.sale_price >= :minPrice)
               AND(:maxPrice IS NULL OR p.sale_price <= :maxPrice)
               AND (product_name like concat('%', :search, '%'))
            order by\s
                CASE WHEN :sortDir = 'asc' THEN p.sale_price END asc,
                CASE WHEN :sortDir = 'desc' THEN p.sale_price END desc
            limit :offset, :pageSize
           \s""", nativeQuery = true)
    List<Product> findProductWithFilter(
            @Param("search") String search,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("sortDir") String sortDir,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );

    @Query(value = """
            select count(*)\s
                from products p
                where (:minPrice IS NULL OR p.sale_price >= :minPrice)
                   AND(:maxPrice IS NULL OR p.sale_price <= :maxPrice)
                   AND (product_name like concat('%', :search, '%'))
           \s""", nativeQuery = true)
    long countProductsWithFilter(
            @Param("search") String search,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );
}
