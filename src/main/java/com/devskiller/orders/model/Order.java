package com.devskiller.orders.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {

  private final String id;

  private final Set<OrderLine> orderLines = new HashSet<>();

  private final Customer customer;

  public Order(String id, Customer customer) {
    this.id = id;
    this.customer = customer;
  }

  public Order addOrderLine(Product product, Integer quantity) {
    orderLines.add(new OrderLine(product, quantity));
    return this;
  }

  public String getId() {
    return id;
  }

  public Set<OrderLine> getOrderLines() {
    return orderLines;
  }

  public Customer getCustomer() {
    return customer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(id, order.id) &&
        Objects.equals(orderLines, order.orderLines) &&
        Objects.equals(customer, order.customer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderLines, customer);
  }
}
