package com.hoang.shopapp.controller;

import com.hoang.shopapp.dto.ProductRequest;
import com.hoang.shopapp.entity.Product;
import com.hoang.shopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll());
        return "product";
    }








    @GetMapping("/product/{id}")
    public Product getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.save(productRequest));
    }

    @PutMapping("/product/{id}")
    public Product update(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }


    // Lấy danh sách sản phẩm theo danh mục
    @GetMapping("/by-category/{id}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Integer id) {
        List<Product> result = productService.getProductsByCategoryId(id);


        return ResponseEntity.ok(result);
    }
}
