package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class RefundOrderCommand implements OrderCommand{
    private final String customerId;
    private final Money orderTotal;

    public RefundOrderCommand(String customerId, Money orderTotal) {
        this.customerId = customerId;
        this.orderTotal = orderTotal;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }
}
