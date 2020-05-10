package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.EntityWithMetadata;
import io.eventuate.EntityWithIdAndVersion;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public class OrderServiceImpl implements OrderService {

  private final AggregateRepository<Order, OrderCommand> orderRepository;
  private CustomerService customerService;

  public OrderServiceImpl(AggregateRepository<Order, OrderCommand> orderRepository, CustomerService customerService) {
    this.orderRepository = orderRepository;
    this.customerService = customerService;
  }

  @Override
  public EntityWithIdAndVersion<Order>
        createOrder(String customerId, Money orderTotal) {
    customerService.verifyCustomerCustomerId(customerId);
    return orderRepository.save(new CreateOrderCommand(customerId, orderTotal));
  }

  @Override
  public EntityWithIdAndVersion<Order>
      refundOrder(String customerId, String orderId, Money orderTotal){
    customerService.verifyCustomerCustomerId(customerId);
    return orderRepository.save(new RefundOrderCommand(customerId, orderId, orderTotal));
  }

  @Override
  public EntityWithMetadata<Order> findById(String orderId) {
    return orderRepository.find(orderId);
  }

  @Override
  public EntityWithIdAndVersion<Order> deleteOrder(String orderId) {
    return orderRepository.update(orderId, new DeleteOrderCommand());
  }
}
