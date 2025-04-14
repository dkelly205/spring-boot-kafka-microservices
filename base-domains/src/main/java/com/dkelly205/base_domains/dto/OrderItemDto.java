package com.dkelly205.base_domains.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {

    private Long productId;
    private int quantity;
}
