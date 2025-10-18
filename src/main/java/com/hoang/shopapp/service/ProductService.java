package com.hoang.shopapp.service;

import com.hoang.shopapp.dto.ProductRequest;
import com.hoang.shopapp.entity.Category;
import com.hoang.shopapp.entity.Product;
import com.hoang.shopapp.repository.CategoryRepository;
import com.hoang.shopapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }


    // Lấy tất cả sản phẩm theo bộ lọc
    private static final int pageSize = 10;

    public List<Product> findProductWithFilter(
            String slug, String search, String priceOption,
            String minPrice, String maxPrice,
            String sortPrice, int page
    ) {
        int offset = pageSize * (page - 1);

        BigDecimal min = null;
        BigDecimal max = null;

        if (priceOption != null) {
            // Tách minPrice, maxPrice
            BigDecimal[] parts = getRangeValues(priceOption);

            if (parts != null) {
                min = parts[0];
                max = parts[1];
            }
        } else if (minPrice != null && maxPrice != null) {
            min = new BigDecimal(minPrice);
            max = new BigDecimal(maxPrice);
        }

        return productRepo.findProductWithFilter(slug, search, min, max, sortPrice, offset, pageSize);
    }

    // Lấy số sản phẩm lọc được
    public int getTotalPages(String slug, String search, String priceOption, String minPrice, String maxPrice) {
        BigDecimal min = null;
        BigDecimal max = null;
        if (priceOption != null) {
            // Tách minPrice, maxPrice
            BigDecimal[] parts = getRangeValues(priceOption);

            if (parts != null) {
                min = parts[0];
                max = parts[1];
            }
        } else if (minPrice != null && maxPrice != null) {
            min = new BigDecimal(minPrice);
            max = new BigDecimal(maxPrice);
        }
        long total = productRepo.countProductsWithFilter(slug, search, min, max);

        return (int) Math.ceil((double) total / pageSize);
    }


    public Product save(ProductRequest productRequest) {
        if (productRequest.getQuantity() < 0) {
            throw new IllegalArgumentException("Số lượng không được âm");
        }

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());             // BigDecimal
        product.setDiscountPercent(productRequest.getDiscountPercent());
        product.setQuantity(productRequest.getQuantity());
        product.setBrand(productRequest.getBrand());
        product.setOrigin(productRequest.getOrigin());
        product.setImageSrc(productRequest.getImageSrc());
        product.setDescription(productRequest.getDescription());


        // Lấy category từ category_id
        Category category = categoryRepository.findByCategoryId(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        return productRepo.save(product);
    }

    public Product update(int productId, ProductRequest productRequest) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (productRequest.getQuantity() < 0) {
            throw new IllegalArgumentException("Số lượng không được âm");
        }

        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());             // BigDecimal
        product.setDiscountPercent(productRequest.getDiscountPercent());
        product.setQuantity(productRequest.getQuantity());
        product.setBrand(productRequest.getBrand());
        product.setOrigin(productRequest.getOrigin());
        product.setImageSrc(productRequest.getImageSrc());
        product.setDescription(productRequest.getDescription());

        Category category = categoryRepository.findByCategoryId(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);

        return productRepo.save(product);
    }


    public void delete(Integer id) {
        productRepo.deleteById(id);
    }

    // Lấy danh sách sản phẩm có mã danh mục là categoryId
    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return productRepo.findByCategory_CategoryId(categoryId);
    }


    /// HELPER ////
    ///      /////

    // Khai báo Map
    private static final LinkedHashMap<String, String> PRICE_OPTION_NAMES = new LinkedHashMap<>();

    // Khối static để khởi tạo và chèn các giá trị theo đúng thứ tự
    static {
        PRICE_OPTION_NAMES.put("duoi-500k", "Dưới 500K");
        PRICE_OPTION_NAMES.put("500k-1trieu", "500K - 1 triệu");
        PRICE_OPTION_NAMES.put("1trieu-3trieu", "1 triệu - 3 triệu");
        PRICE_OPTION_NAMES.put("3trieu-5trieu", "3 triệu - 5 triệu");
        PRICE_OPTION_NAMES.put("tren-5trieu", "Trên 5 triệu");
    }

    // Khai báo một Map tĩnh để lưu trữ dải giá (Dữ liệu số)
    // Giá trị là [minPrice, maxPrice]. Null cho maxPrice nghĩa là "trên"
    private static final Map<String, BigDecimal[]> PRICE_RANGES = Map.of(
            "duoi-500k", new BigDecimal[]{BigDecimal.ZERO, new BigDecimal("500000")},
            "500k-1trieu", new BigDecimal[]{new BigDecimal("500000"), new BigDecimal("1000000")},
            "1trieu-3trieu", new BigDecimal[]{new BigDecimal("1000000"), new BigDecimal("3000000")},
            "3trieu-5trieu", new BigDecimal[]{new BigDecimal("3000000"), new BigDecimal("5000000")},
            "tren-5trieu", new BigDecimal[]{new BigDecimal("5000000"), null}
    );

    /**
     * Trả về map cho frontend
     */
    public static LinkedHashMap<String, String> getAllPriceOptions() {
        return PRICE_OPTION_NAMES;
    }


    /**
     * Lấy dải giá (min và max) cho truy vấn cơ sở dữ liệu.
     *
     * @param rangeParam Tham số từ URL (ví dụ: "duoi-500k").
     * @return Mảng [minPrice, maxPrice] dưới dạng BigDecimal, hoặc null nếu không khớp.
     */
    public static BigDecimal[] getRangeValues(String rangeParam) {
        if (rangeParam == null || rangeParam.isBlank()) {
            return null;
        }
        return PRICE_RANGES.get(rangeParam.toLowerCase());
    }

    /**
     * Lấy tên hiển thị cho Frontend.
     *
     * @param rangeParam Tham số từ URL (ví dụ: "duoi-500k").
     * @return Tên hiển thị (String), hoặc null nếu không khớp.
     */
    public static String getOptionName(String rangeParam) {
        if (rangeParam == null || rangeParam.isBlank()) {
            return null;
        }
        return PRICE_OPTION_NAMES.get(rangeParam.toLowerCase());
    }
}
