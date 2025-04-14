package com.dkelly205.order_service.service.impl;

import com.dkelly205.order_service.dto.OrderRequest;
import com.dkelly205.order_service.dto.OrderResponse;
import com.dkelly205.order_service.entity.CartOrder;
import com.dkelly205.order_service.entity.OutboxEvent;
import com.dkelly205.order_service.enums.OutboxStatus;
import com.dkelly205.order_service.mapper.OrderMapper;
import com.dkelly205.order_service.repository.OrderRepository;
import com.dkelly205.order_service.repository.OutboxRepository;
import com.dkelly205.order_service.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OutboxRepository outboxRepository;



    public OrderResponse create(OrderRequest orderRequest) {
        CartOrder cartOrder = orderMapper.mapToEntity(orderRequest);
        CartOrder savedCartOrder = save(cartOrder);
        return orderMapper.mapToResponse(savedCartOrder);
    }

    @Transactional
    private CartOrder save(CartOrder cartOrder){
        CartOrder savedCartOrder = orderRepository.save(cartOrder);

        OutboxEvent outboxEvent = OutboxEvent.builder()
                .cartOrder(cartOrder)
                .status(OutboxStatus.PENDING)
                .build();

        outboxRepository.save(outboxEvent);

        return savedCartOrder;
    }
}
