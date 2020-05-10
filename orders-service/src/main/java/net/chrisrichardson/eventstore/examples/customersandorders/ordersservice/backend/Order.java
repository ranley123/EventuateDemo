package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.eventstore.examples.customersandorders.common.order.*;

import java.util.Collections;
import java.util.List;

import static io.eventuate.EventUtil.events;

public class Order
        extends ReflectiveMutableCommandProcessingAggregate<Order, OrderCommand> {

  private OrderState state;
  private String customerId;
  private Money cost;
  private Boolean deleted = false;

  public Money getCost() {
    return cost;
  }

  public String getCustomerId() {
    return customerId;
  }

  public List<Event> process(CreateOrderCommand cmd) {
    return events(new OrderCreatedEvent(cmd.getCustomerId(), cmd.getOrderTotal()));
  }

  public void apply(OrderCreatedEvent event) {
    this.state = OrderState.CREATED;
    this.customerId = event.getCustomerId();
    this.cost = event.getOrderTotal();
  }

  public List<Event> process(DeleteOrderCommand cmd){
    if(this.deleted)
      return Collections.emptyList();

    return EventUtil.events(new OrderDeletedEvent());
  }

  public void apply(OrderDeletedEvent event){
    this.deleted = true;
    this.cost = event.getOrderCost();
  }

  public List<Event> process(RefundOrderCommand cmd){
    return events(new OrderRefundedEvent(cmd.getCustomerId(), cmd.getOrderId(), cmd.getOrderTotal()));
  }

  public void apply(OrderRefundedEvent event){
    this.state = OrderState.REFUNDED;
  }

  public OrderState getState() {
    return state;
  }

  public List<Event> process(ApproveOrderCommand cmd) {
    return events(new OrderApprovedEvent(customerId));
  }

  public List<Event> process(RejectOrderCommand cmd) {
    return events(new OrderRejectedEvent(customerId));
  }


  public void apply(OrderApprovedEvent event) {
    this.state = OrderState.APPROVED;
  }


  public void apply(OrderRejectedEvent event) {
    this.state = OrderState.REJECTED;
  }

}
