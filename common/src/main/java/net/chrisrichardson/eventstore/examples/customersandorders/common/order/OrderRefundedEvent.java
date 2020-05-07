package net.chrisrichardson.eventstore.examples.customersandorders.common.order;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class OrderRefundedEvent implements OrderEvent{
    private Money orderTotal;
    private String customerId;

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public OrderRefundedEvent() {
    }

    public OrderRefundedEvent(String customerId, Money orderTotal) {
        this.customerId = customerId;
        this.orderTotal = orderTotal;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

    public String getCustomerId() {
        return customerId;
    }
}
