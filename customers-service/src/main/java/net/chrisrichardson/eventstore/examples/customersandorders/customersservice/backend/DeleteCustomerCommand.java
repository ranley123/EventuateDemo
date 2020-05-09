package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class DeleteCustomerCommand implements CustomerCommand {
    private final String name;
    private final Money creditLimit;

    public DeleteCustomerCommand(){
        //set attributes to null so that any future attempt to access them returns no actual values
        this.name = null;
        this.creditLimit = null;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public String getName() {
        return name;
    }
}
