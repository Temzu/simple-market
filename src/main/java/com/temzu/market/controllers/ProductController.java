package com.temzu.market.controllers;

import com.temzu.market.model.dtos.ProductDto;
import com.temzu.market.repositories.specifications.ProductSpecifications;
import com.temzu.market.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "s", defaultValue = "10") Integer pageSize
    ) {
        if (page < 1 || pageSize < 1) {
            page = 1;
            pageSize = 10;
        }
        return productService.getAllProducts(ProductSpecifications.build(params), page, pageSize);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productService.saveOrUpdate(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
