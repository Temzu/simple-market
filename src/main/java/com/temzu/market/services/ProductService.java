package com.temzu.market.services;

import com.temzu.market.model.dtos.ProductDto;
import com.temzu.market.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {
    Page<ProductDto> getAllProducts(Specification<Product> spec, int page, int pageSize);

    Optional<Product> getProductById(Long id);

    ProductDto getProductDtoById(Long id);

    void deleteProductById(Long id);

    ProductDto saveOrUpdate(ProductDto productDto);
}
