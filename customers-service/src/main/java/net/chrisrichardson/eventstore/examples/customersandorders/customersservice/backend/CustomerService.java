package net.chrisrichardson.eventstore.examples.customersandorders.customersservice.backend;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import net.chrisrichardson.eventstore.examples.customersandorders.common.domain.Money;

public interface CustomerService {

  EntityWithIdAndVersion<Customer> createCustomer(String name, Money creditLimit);

  EntityWithIdAndVersion<Customer> updateCustomer(String customerId, String name, Money creditLimit);

  EntityWithIdAndVersion<Customer> deleteCustomer(String customerId);

  EntityWithMetadata<Customer> findById(String customerId);
}
