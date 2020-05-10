package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class DeleteOrderCommand implements OrderCommand {
    private final Money cost;

    public DeleteOrderCommand(){
        this.cost = null;
    }

    public Money getCost() {
        return cost;
    }
}