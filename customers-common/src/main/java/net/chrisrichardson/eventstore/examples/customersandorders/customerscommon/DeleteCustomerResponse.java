package net.chrisrichardson.eventstore.examples.customersandorders.customerscommon;

public class DeleteCustomerResponse {
    private String customerId;

    public DeleteCustomerResponse(){

    }

    public DeleteCustomerResponse(String customerId){
        this.customerId = customerId;
    }

    public String getCustomerId(){
        return customerId;
    }
}