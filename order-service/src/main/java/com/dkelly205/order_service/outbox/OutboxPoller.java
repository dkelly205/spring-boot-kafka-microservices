package com.dkelly205.order_service.outbox;

import com.dkelly205.base_domains.dto.OrderCreatedEvent;
import com.dkelly205.order_service.entity.OutboxEvent;
import com.dkelly205.order_service.enums.OutboxStatus;
import com.dkelly205.order_service.kafka.OrderProducer;
import com.dkelly205.order_service.mapper.OrderMapper;
import com.dkelly205.order_service.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxPoller {

    private final OrderProducer orderProducer;
    private final OrderMapper orderMapper;
    private final OutboxRepository outboxRepository;

    @Scheduled(fixedDelay = 60000)
    public void poll(){

        List<OutboxEvent> outboxEvents = outboxRepository.findAllByStatusAndAttemptsLessThan(OutboxStatus.PENDING, 3);

        for(OutboxEvent outboxEvent : outboxEvents){
            outboxEvent.setAttempts(outboxEvent.getAttempts() + 1);

            try{
                OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                        .orderId(outboxEvent.getCartOrder().getId())
                        .email(outboxEvent.getCartOrder().getEmail())
                        .name(outboxEvent.getCartOrder().getName())
                        //.items(outboxEvent.getCartOrder().getItems())
                        .build();

                orderProducer.sendMessage(orderCreatedEvent);
                outboxEvent.setStatus(OutboxStatus.PROCESSED);
            }  catch (Exception e) {
                if (outboxEvent.getAttempts() >= 3) {
                    outboxEvent.setStatus(OutboxStatus.FAILED);
                }
            }

            outboxRepository.save(outboxEvent);
        }

    }
}
