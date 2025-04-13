package com.dkelly205.order_service.controller;

import com.dkelly205.base_domains.dto.OrderDto;
import com.dkelly205.order_service.kafka.OrderProducer;
import com.dkelly205.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestBody @Valid OrderDto orderDto){
        OrderDto savedOrderDto = orderService.create(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderDto);
    }
}
