package com.hoang.shopapp.controller;


import com.hoang.shopapp.entity.Category;
import com.hoang.shopapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    public Category create(@RequestBody Map<String, String> body) {
        String categoryName = body.get("categoryName");
        return categoryService.create(categoryName);
    }

    @PutMapping("/category/{id}")
    public Category update(@PathVariable Integer id, @RequestBody Map<String, String> body){
        return categoryService.update(id, body.get("categoryName"));
    }

    @DeleteMapping("/category/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }

    @GetMapping("/category-with-count")
    public List<Map<String, Objects>> getProductCountByCategory() {
        return categoryService.getCategoryWithProductCount();
    }
}
