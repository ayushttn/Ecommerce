package com.ecommerce.Entities;

import javax.persistence.*;

enum fromStatus {
    ORDER_PLACED, CANCELLED, ORDER_REJECTED, ORDER_CONFIRMED, ORDER_SHIPPED, DELIVERED, RETURN_REQUESTED, RETURN_REJECTED, RETURN_APPROVED, PICK_UP_INITIATED, PICK_UP_COMPLETED, REFUND_INITIATED, REFUND_COMPLETED;
}

enum toStatus {
    CANCELLED, ORDER_CONFIRMED, ORDER_REJECTED, REFUND_INITIATED, CLOSED, ORDER_SHIPPED, DELIVERED, RETURN_REQUESTED, RETURN_REJECTED, RETURN_APPROVED, PICK_UP_INITIATED, PICK_UP_COMPLETED, REFUND_COMPLETED;
}

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private OrderProduct orderProduct;

    @Enumerated(EnumType.STRING)
    private fromStatus fromStatus;

    @Enumerated(EnumType.STRING)
    private toStatus toStatus;

    private String transitionNotesComments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public com.ecommerce.Entities.fromStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(com.ecommerce.Entities.fromStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public com.ecommerce.Entities.toStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(com.ecommerce.Entities.toStatus toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransitionNotesComments() {
        return transitionNotesComments;
    }

    public void setTransitionNotesComments(String transitionNotesComments) {
        this.transitionNotesComments = transitionNotesComments;
    }
}