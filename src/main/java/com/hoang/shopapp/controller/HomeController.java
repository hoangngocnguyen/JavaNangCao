package com.hoang.shopapp.controller;

import com.hoang.shopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;


    @GetMapping({"/", "/products"})
    public String home(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false) String priceOption,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) String sortPrice,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {
        int totalPages = productService.getTotalPages(search, priceOption, minPrice, maxPrice);

        model.addAttribute("products", productService.findProductWithFilter(search, priceOption, minPrice, maxPrice, sortPrice, page));
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("search", search);
        model.addAttribute("currentPage", page);
        model.addAttribute("sortPrice", sortPrice);

        model.addAttribute("priceOptionsMap", ProductService.getAllPriceOptions());
        model.addAttribute("priceOption", priceOption);
        model.addAttribute("priceOptionName", ProductService.getOptionName(priceOption));

        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);


        return "index";
    }
}
