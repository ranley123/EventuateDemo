package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class UpdateCustomerCommand implements CustomerCommand{
    private final String name;
    private final Money creditLimit;

    public UpdateCustomerCommand(String name, Money creditLimit){
        this.name = name;
        this.creditLimit = creditLimit;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public String getName() {
        return name;
    }
}