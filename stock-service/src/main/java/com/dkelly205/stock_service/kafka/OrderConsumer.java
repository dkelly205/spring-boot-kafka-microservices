package com.dkelly205.stock_service.kafka;

import com.dkelly205.base_domains.dto.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderCreatedEvent event){

        LOGGER.info(String.format("Consuming order event in stock service => %s", event.toString()));

        //save event


    }
}
