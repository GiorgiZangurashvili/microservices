package com.zangura.mapper;

import com.zangura.domain.Product;
import com.zangura.dto.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {
    ProductResponse mapProductToProductResponse(Product product);
    Product mapProductResponseToProduct(ProductResponse productRequest);
    List<Product> mapDTOsToEntities(List<ProductResponse> products);
    List<ProductResponse> mapEntitiesToDTOs(List<Product> productRequests);
}
