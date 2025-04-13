package com.dkelly205.order_service.repository;

import com.dkelly205.order_service.entity.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CartOrder, Long> {
}
