package com.dkelly205.base_domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {

    private Long orderId;
    private String name;
    private String email;
    private List<OrderItemDto> items;
}
