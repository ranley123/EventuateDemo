package net.chrisrichardson.eventstore.examples.customersandorders.customerscommon;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class DeleteCustomerRequest {
    private String name;
    private Money creditLimit;

    public DeleteCustomerRequest(){

    };

    public DeleteCustomerRequest(String name, Money creditLimit){
        this.name = name;
        this.creditLimit = creditLimit;
    }

    public String getName() {
        return name;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }
}
