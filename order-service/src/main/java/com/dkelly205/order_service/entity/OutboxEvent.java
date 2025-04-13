package com.dkelly205.order_service.entity;

import com.dkelly205.order_service.enums.OutboxStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="outbox_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_order_id", referencedColumnName = "id")
    private CartOrder cartOrder;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    private int attempts;

    
}
