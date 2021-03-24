package com.temzu.market.services;

import com.temzu.market.exceptions.PageDeterminationException;
import com.temzu.market.exceptions.ProductNotFoundException;
import com.temzu.market.model.dtos.ProductDto;
import com.temzu.market.model.entities.Product;
import com.temzu.market.model.mappers.ProductMapper;
import com.temzu.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductMapper mapper;
    private ProductRepository productRepository;

    @Override
    public Page<ProductDto> getAllProducts(Specification<Product> spec, int page, int pageSize) {
        Page<ProductDto> products = productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(mapper::toProductDto);
        int totalPages = products.getTotalPages();
        if (totalPages < page) {
            throw new PageDeterminationException(
                    String.format("Page not found: page %d, size %d, total pages %d", page, pageSize, totalPages));
        }
        return products;
    }

    @Override
    public ProductDto getProductById(Long id) {
        return mapper.toProductDto(productRepository
                .findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: \"" + id + "\" not found")));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto productDto) {
        Product product = mapper.toProduct(productDto);
        return mapper.toProductDto(productRepository.save(product));
    }
}
