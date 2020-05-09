package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.eventstore.examples.customersandorders.common.customer.*;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

import java.util.Collections;
import java.util.List;

public class Customer extends ReflectiveMutableCommandProcessingAggregate<Customer, CustomerCommand> {

  private ReservedCreditTracker reservedCreditTracker;

  private Money creditLimit;
  private String name;
  private boolean deleted = false;

  public Money availableCredit() {
    return creditLimit.subtract(reservedCreditTracker.reservedCredit());
  }


  public Money getCreditLimit() {
    return creditLimit;
  }

  public List<Event> process(CreateCustomerCommand cmd) {
    return EventUtil.events(new CustomerCreatedEvent(cmd.getName(), cmd.getCreditLimit()));
  }

  public List<Event> process(ReserveCreditCommand cmd) {
    if (availableCredit().isGreaterThanOrEqual(cmd.getOrderTotal()))
      return EventUtil.events(new CustomerCreditReservedEvent(cmd.getOrderId(), cmd.getOrderTotal()));
    else
      return EventUtil.events(new CustomerCreditLimitExceededEvent(cmd.getOrderId()));
  }

  public List<Event> process(RefundCreditCommand cmd){
    return EventUtil.events(new CustomerCreditRefundedEvent(cmd.getOrderId(), cmd.getOrderTotal()));
  }

  public List<Event> process(UpdateCustomerCommand cmd){
    if(this.deleted)
      return Collections.emptyList();

    return EventUtil.events(new CustomerUpdatedEvent(cmd.getName(), cmd.getCreditLimit()));
  }

  public List<Event> process(DeleteCustomerCommand cmd){
    if(this.deleted)
      return Collections.emptyList();

    return EventUtil.events(new CustomerDeletedEvent());
  }


  public void apply(CustomerCreatedEvent event) {
    this.name = name;
    this.creditLimit = event.getCreditLimit();
    this.reservedCreditTracker = new ReservedCreditTracker();
  }

  public void apply(CustomerCreditReservedEvent event) {
    reservedCreditTracker.addReservation(event.getOrderId(), event.getOrderTotal());
  }

  public void apply(CustomerCreditRefundedEvent event){
    reservedCreditTracker.removeReservation(event.getOrderId(), event.getOrderTotal());
  }

  public void apply(CustomerCreditLimitExceededEvent event) {
    // Do nothing
  }

  public void apply(CustomerUpdatedEvent event){
    this.name = event.getName();
    this.creditLimit = event.getCreditLimit();
  }

  public void apply(CustomerDeletedEvent event){
    this.deleted = true;
    this.name = event.getName();
    this.creditLimit = event.getCreditLimit();
  }


  public String getName() {
    return name;
  }
}



