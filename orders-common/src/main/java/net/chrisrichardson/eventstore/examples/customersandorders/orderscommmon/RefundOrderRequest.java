package net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class RefundOrderRequest {
    private Money orderTotal;
    private String customerId;
    private String orderId;

    public RefundOrderRequest() {
    }

    public RefundOrderRequest(String customerId, String orderId, Money orderTotal) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderId(){
        return orderId;
    }
}
