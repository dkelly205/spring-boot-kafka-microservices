package com.dkelly205.base_domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private String orderId;
    private String name;
    private int quantity;
    private double price;
}
