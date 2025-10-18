package com.hoang.shopapp.controller;


import com.hoang.shopapp.entity.Category;
import com.hoang.shopapp.service.CategoryService;
import com.hoang.shopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/danh-muc")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/{slug}")
    public String category (
            @PathVariable String slug,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false) String priceOption,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) String sortPrice,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {

        model.addAttribute("products", productService.findProductWithFilter(slug, search, priceOption, minPrice, maxPrice, sortPrice, page));
        model.addAttribute("totalPages", productService.getTotalPages(slug, search, priceOption, minPrice, maxPrice));
        model.addAttribute("search", search);
        model.addAttribute("currentPage", page);
        model.addAttribute("sortPrice", sortPrice);

        // Trả về map priceOption
        model.addAttribute("priceOptionsMap", ProductService.getAllPriceOptions());
        model.addAttribute("priceOption", priceOption);
        model.addAttribute("priceOptionName", ProductService.getOptionName(priceOption));

        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        

        // Lấy thông tin category trả về client
        Category category = categoryService.findBySlug(slug);

        model.addAttribute("categoryName", category.getCategoryName());
        model.addAttribute("categorySlug", category.getSlug());

        return "index";
    }
//    @GetMapping
//    public List<Category> getAll() {
//        return categoryService.getAll();
//    }
//
//    @PostMapping
//    public Category create(@RequestBody Map<String, String> body) {
//        String categoryName = body.get("categoryName");
//        return categoryService.create(categoryName);
//    }
//
//    @PutMapping("/category/{id}")
//    public Category update(@PathVariable Integer id, @RequestBody Map<String, String> body){
//        return categoryService.update(id, body.get("categoryName"));
//    }
//
//    @DeleteMapping("/category/{id}")
//    public void delete(@PathVariable Integer id) {
//        categoryService.delete(id);
//    }
//
//    @GetMapping("/category-with-count")
//    public List<Map<String, Objects>> getProductCountByCategory() {
//        return categoryService.getCategoryWithProductCount();
//    }
}
