package com.hoang.shopapp.service;

import com.hoang.shopapp.entity.Category;
import com.hoang.shopapp.entity.Product;
import com.hoang.shopapp.repository.CategoryRepository;
import com.hoang.shopapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    public void delete(Integer id) {
        // Không xóa danh mục đang gắn với sản phẩm.
        List<Product> products = productRepository.findByCategory_CategoryId(id);
        if (products.isEmpty()) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Không thể xóa danh mục đang chứa sản phẩm");
        }
    }

    public Category create(String categoryName){
        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryName)) {
            throw new RuntimeException("Category already exists");
        }

        Category category = new Category();
        category.setCategoryName(categoryName);
        return categoryRepository.save(category);
    }

    public Category update(int id, String newCategoryName){
        Category category = categoryRepository.findByCategoryId(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(newCategoryName);

        return categoryRepository.save(category);
    }

    public List<Map<String, Objects>> getCategoryWithProductCount() {
        return categoryRepository.getCategoryWithProductCount();
    }
}
