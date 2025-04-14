package com.dkelly205.order_service.mapper;

import com.dkelly205.order_service.dto.OrderRequest;
import com.dkelly205.order_service.dto.OrderResponse;
import com.dkelly205.order_service.entity.CartOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    CartOrder mapToEntity(OrderRequest orderRequest);

    @Mapping(source = "id", target = "orderId")
    OrderResponse mapToResponse(CartOrder cartOrder);


}
