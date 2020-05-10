package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.web;

import io.eventuate.EntityNotFoundException;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.CreateOrderResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.RefundOrderRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.RefundOrderResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.GetOrderResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.DeleteOrderResponse;
import net.chrisrichardson.eventstore.examples.customersandorders.orderscommmon.DeleteOrderRequest;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.CustomerNotFoundException;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.Order;
import net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        try {
            EntityWithIdAndVersion<Order> order =
                    orderService.createOrder(createOrderRequest.getCustomerId(), createOrderRequest.getOrderTotal());
            return new ResponseEntity<>(new CreateOrderResponse(order.getEntityId()), HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/orders", method = RequestMethod.PUT)
    public ResponseEntity<RefundOrderResponse> refundOrder(@RequestBody RefundOrderRequest refundOrderRequest){
        try {
            EntityWithIdAndVersion<Order> order =
                    orderService.refundOrder(refundOrderRequest.getCustomerId(), refundOrderRequest.getOrderId(), refundOrderRequest.getOrderTotal());
            return new ResponseEntity<>(new RefundOrderResponse(order.getEntityId()), HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable String orderId) {
        EntityWithMetadata<Order> orderWithMetadata;
        try {
            orderWithMetadata = orderService.findById(orderId);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderWithMetadata.getEntity();
        GetOrderResponse response =
                new GetOrderResponse(orderWithMetadata.getEntityIdAndVersion().getEntityId(), order.getCustomerId(),
                        order.getCost(), order.getState());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.DELETE)
    public ResponseEntity<DeleteOrderResponse> deleteOrder(@PathVariable String orderId){
        EntityWithIdAndVersion<Order> order;
        try {
        order = orderService.deleteOrder(orderId);
        } catch (EntityNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        DeleteOrderResponse response = new DeleteOrderResponse(order.getEntityId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
