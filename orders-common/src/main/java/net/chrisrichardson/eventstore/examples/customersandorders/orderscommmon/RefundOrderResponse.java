package net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon;

public class RefundOrderResponse {
    private String orderId;

    public RefundOrderResponse() {
    }

    public RefundOrderResponse(String orderId) {
        this.orderId = orderId;

    }

    public String getOrderId() {
        return orderId;
    }
}
