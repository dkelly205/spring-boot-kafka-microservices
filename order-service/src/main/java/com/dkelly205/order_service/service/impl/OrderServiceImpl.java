package com.dkelly205.order_service.service.impl;

import com.dkelly205.base_domains.dto.OrderDto;
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



    public OrderDto create(OrderDto orderDto) {
        CartOrder cartOrder = orderMapper.mapToEntity(orderDto);
        CartOrder savedCartOrder = save(cartOrder);
        return orderMapper.mapToDto(savedCartOrder);
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
