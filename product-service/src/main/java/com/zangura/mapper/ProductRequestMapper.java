package com.zangura.mapper;

import com.zangura.domain.Product;
import com.zangura.dto.ProductRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
    ProductRequest mapProductToProductRequest(Product product);
    Product mapProductRequestToProduct(ProductRequest productRequest);
    List<Product> mapDTOsToEntities(List<ProductRequest> products);
    List<ProductRequest> mapEntitiesToDTOs(List<Product> productRequests);
}
