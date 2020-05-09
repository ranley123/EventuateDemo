package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class RefundOrderCommand implements OrderCommand{
    private final String customerId;
    private final String orderId;
    private final Money orderTotal;

    public RefundOrderCommand(String customerId, String orderId, Money orderTotal) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderId(){
        return orderId;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }
}
