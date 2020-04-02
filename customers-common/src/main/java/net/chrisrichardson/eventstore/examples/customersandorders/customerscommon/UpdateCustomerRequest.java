package net.chrisrichardson.eventstore.examples.customersandorders.customerscommon;

import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class UpdateCustomerRequest {
    private String name;
    private Money creditLimit;

    public UpdateCustomerRequest(){

    };

    public UpdateCustomerRequest(String name, Money creditLimit){
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
