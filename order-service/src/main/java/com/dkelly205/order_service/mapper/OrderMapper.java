package com.dkelly205.order_service.mapper;

import com.dkelly205.base_domains.dto.OrderDto;
import com.dkelly205.base_domains.dto.OrderEvent;
import com.dkelly205.order_service.entity.CartOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(source = "cartDto", target = "cart")
    @Mapping(source = "orderId", target = "id")
    CartOrder mapToEntity(OrderDto orderDto);

    @Mapping(source = "cart", target = "cartDto")
    @Mapping(source = "id", target = "orderId")
    OrderDto mapToDto(CartOrder cartOrder);


}
