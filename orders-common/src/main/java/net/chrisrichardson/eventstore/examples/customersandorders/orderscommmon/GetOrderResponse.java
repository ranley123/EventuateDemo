package net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.OrderState;

public class GetOrderResponse {
    private String customerId;
    private String orderId;
    private Money cost;
    private OrderState state;

    public GetOrderResponse() {
    }

    public GetOrderResponse(String orderId, String customerId, Money cost, OrderState state) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.cost = cost;
        this.state = state;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Money getCost() {
        return cost;
    }

    public void setCost(Money cost) {
        this.cost = cost;
    }

    public String getOrderId() {return orderId;}

    public void setOrderId(String orderId) {this.orderId = orderId;}

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }
}