package net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class DeleteOrderRequest {
    private Money cost;

    public DeleteOrderRequest(){
    };

    public DeleteOrderRequest(Money cost){
        this.cost = cost;
    }

    public Money getCost() {
        return cost;
    }
}