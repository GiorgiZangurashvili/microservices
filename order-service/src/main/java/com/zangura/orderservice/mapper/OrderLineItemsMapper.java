package com.zangura.orderservice.mapper;

import com.zangura.orderservice.domain.OrderLineItems;
import com.zangura.orderservice.dto.OrderLineItemsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderLineItemsMapper {
    OrderLineItems mapDtoToEntity(OrderLineItemsDTO dto);
    OrderLineItemsDTO mapEntityToDto(OrderLineItems entity);
    List<OrderLineItems> mapDtosToEntities(List<OrderLineItemsDTO> dtos);
    List<OrderLineItemsDTO> mapEntitiesToDtos(List<OrderLineItems> entities);
}
