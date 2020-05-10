package net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon;

public class DeleteOrderResponse {
    private String orderId;

    public DeleteOrderResponse(){
    }

    public DeleteOrderResponse(String orderId){
        this.orderId = orderId;
    }

    public String getOrderId(){
        return orderId;
    }

    public boolean getCanceled() {
        return true;
    }
}