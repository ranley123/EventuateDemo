package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class RefundCreditCommand implements CustomerCommand{
    private final Money orderTotal;
    private final String orderId;

    public RefundCreditCommand(Money orderTotal, String orderId) {
        this.orderTotal = orderTotal;
        this.orderId = orderId;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

    public String getOrderId() {
        return orderId;
    }
}
