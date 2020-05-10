package net.chrisrichardson.eventstore.examples.customersandorders.ordersservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public interface OrderService {

    EntityWithIdAndVersion<Order> createOrder(String customerId, Money orderTotal);

    EntityWithIdAndVersion<Order> refundOrder(String customerId, String orderId, Money orderTotal);

    EntityWithMetadata<Order> findById(String orderId);

    EntityWithIdAndVersion<Order> deleteOrder(String orderId);
}
