package com.dkelly205.order_service.dto;

import com.dkelly205.base_domains.dto.OrderItemDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotBlank(message="Name is required")
    private String name;

    @Email(message = "Must be a valid email")
    @NotBlank(message="Email is required")
    private String email;

    private BigDecimal total;

    private List<OrderItemDto> items;
}
