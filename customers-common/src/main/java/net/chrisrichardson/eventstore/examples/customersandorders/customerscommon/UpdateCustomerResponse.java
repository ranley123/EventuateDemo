package net.chrisrichardson.eventstore.examples.customersandorders.customerscommon;

public class UpdateCustomerResponse {
    private String customerId;

    public UpdateCustomerResponse(){

    }

    public UpdateCustomerResponse(String customerId){
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
}
