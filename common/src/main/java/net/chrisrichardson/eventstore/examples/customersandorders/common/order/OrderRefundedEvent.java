package net.chrisrichardson.eventstore.examples.customersandorders.common.order;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class OrderRefundedEvent implements OrderEvent{
    private Money orderTotal;
    private String customerId;
    private String orderId;

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

    public OrderRefundedEvent(String customerId, String orderId, Money orderTotal) {
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
