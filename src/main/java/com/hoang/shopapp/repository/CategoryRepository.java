package com.hoang.shopapp.repository;

import com.hoang.shopapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Boolean existsByCategoryNameIgnoreCase(String name);
    public Optional<Category> findByCategoryId(int categoryId);
    public Category findBySlug(String slug);

    @Query(value = """
        select c.category_id, category_name, count(p.category_id) as count
          from products p right join category c on p.category_id = c.category_id
          group by category_id, category_name
    """, nativeQuery = true)
    List<Map<String, Objects>> getCategoryWithProductCount();
}
