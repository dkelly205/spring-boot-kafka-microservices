package com.dkelly205.email_service.kafka;

import com.dkelly205.base_domains.dto.OrderCreatedEvent;
import com.dkelly205.email_service.enums.EmailTemplateName;
import com.dkelly205.email_service.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final EmailService emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderCreatedEvent event) throws MessagingException {

        LOGGER.info(String.format("Consuming order event in email service => %s", event.toString()));

        //TODO. Send email
        emailService.sendEmail(event.getEmail(),
                event.getName(),
                EmailTemplateName.ORDER_CONFIRMATION,
                "order confirmation"
        );



    }
}
