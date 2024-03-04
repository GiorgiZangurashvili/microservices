package com.zangura.productservice.mapper;

import com.zangura.productservice.domain.Product;
import com.zangura.productservice.dto.ProductRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
    ProductRequest mapProductToProductRequest(Product product);
    Product mapProductRequestToProduct(ProductRequest productRequest);
    List<Product> mapDTOsToEntities(List<ProductRequest> products);
    List<ProductRequest> mapEntitiesToDTOs(List<Product> productRequests);
}
