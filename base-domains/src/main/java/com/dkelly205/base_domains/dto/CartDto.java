package com.dkelly205.base_domains.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private List<ItemDto> items;
    private BigDecimal total;
}
