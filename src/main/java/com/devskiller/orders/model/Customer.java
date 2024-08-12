package com.devskiller.orders.model;

import java.util.Objects;

public class Customer {

  private final String id;

  private final String firstName;

  private final String lastName;

  public Customer(String id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id) &&
        Objects.equals(firstName, customer.firstName) &&
        Objects.equals(lastName, customer.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }
}
