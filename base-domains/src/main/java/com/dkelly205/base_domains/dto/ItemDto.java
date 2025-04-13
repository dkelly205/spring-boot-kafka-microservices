package com.dkelly205.base_domains.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    private String name;
    private String description;
    private BigDecimal price;


}
