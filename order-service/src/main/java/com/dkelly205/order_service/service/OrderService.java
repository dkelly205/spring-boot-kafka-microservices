package com.dkelly205.order_service.service;

import com.dkelly205.order_service.dto.OrderRequest;
import com.dkelly205.order_service.dto.OrderResponse;

public interface OrderService {

    OrderResponse create(OrderRequest orderRequest);
}
