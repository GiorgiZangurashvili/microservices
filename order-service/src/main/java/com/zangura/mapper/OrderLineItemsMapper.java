package com.zangura.mapper;

import com.zangura.domain.OrderLineItems;
import com.zangura.dto.OrderLineItemsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderLineItemsMapper {
    OrderLineItems mapDtoToEntity(OrderLineItemsDTO dto);
    OrderLineItemsDTO mapEntityToDto(OrderLineItems entity);
    List<OrderLineItems> mapDtosToEntities(List<OrderLineItemsDTO> dtos);
    List<OrderLineItemsDTO> mapEntitiesToDtos(List<OrderLineItems> entities);
}
