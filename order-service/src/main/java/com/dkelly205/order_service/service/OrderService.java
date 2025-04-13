package com.dkelly205.order_service.service;

import com.dkelly205.base_domains.dto.OrderDto;

public interface OrderService {

    OrderDto create(OrderDto orderDto);
}
