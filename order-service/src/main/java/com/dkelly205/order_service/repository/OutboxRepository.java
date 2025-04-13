package com.dkelly205.order_service.repository;

import com.dkelly205.order_service.entity.OutboxEvent;
import com.dkelly205.order_service.enums.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Long> {

    List<OutboxEvent> findAllByStatusAndAttemptsLessThan(OutboxStatus status, int attempts);

}
