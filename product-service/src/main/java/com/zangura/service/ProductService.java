package com.zangura.service;

import com.zangura.mapper.ProductRequestMapper;
import com.zangura.mapper.ProductResponseMapper;
import com.zangura.repository.ProductRepository;
import com.zangura.domain.Product;
import com.zangura.dto.ProductRequest;
import com.zangura.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    public void createProduct(ProductRequest productRequest) {
        Product product = productRequestMapper.mapProductRequestToProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        log.info("Product with id = {} is saved", savedProduct.getId());
    }

    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        log.info("Retrieved all products");
        return productResponseMapper.mapEntitiesToDTOs(products);
    }
}
