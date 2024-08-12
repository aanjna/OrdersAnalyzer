package com.devskiller.orders.model;

import java.util.Objects;

public class OrderLine {

  private final Product product;

  private final Integer quantity;

  public OrderLine(Product product, Integer quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderLine orderLine = (OrderLine) o;
    return Objects.equals(product, orderLine.product) &&
        Objects.equals(quantity, orderLine.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, quantity);
  }
}
