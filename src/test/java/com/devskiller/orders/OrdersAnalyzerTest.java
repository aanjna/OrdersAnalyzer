package com.devskiller.orders;

import com.devskiller.orders.model.Customer;
import com.devskiller.orders.model.Order;
import com.devskiller.orders.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class OrdersAnalyzerTest {

    private OrdersAnalyzer ordersAnalyzer = new OrdersAnalyzer();

    @Test
    public void shouldReturnThreeMostPopularProducts() {
        // given
        List<Order> orders = new ArrayList<>();
        Customer customer1 = new Customer(randomId(), "John", "Smith");
        Customer customer2 = new Customer(randomId(), "Ben", "Marshall");
        Product book1 = new Product("Book 1", new BigDecimal("23.00"));
        Product book2 = new Product("Book 2", new BigDecimal("44.00"));
        Product book3 = new Product("Book 3", new BigDecimal("99.00"));
        Product book4 = new Product("Book 4", new BigDecimal("39.00"));
        orders.add(new Order(randomId(), customer1).addOrderLine(book1, 1));
        orders.add(new Order(randomId(), customer1).addOrderLine(book2, 1));
        orders.add(new Order(randomId(), customer1).addOrderLine(book3, 1));
        orders.add(new Order(randomId(), customer1).addOrderLine(book4, 1));
        orders.add(new Order(randomId(), customer2).addOrderLine(book1, 1));
        orders.add(new Order(randomId(), customer2).addOrderLine(book2, 1));
        orders.add(new Order(randomId(), customer2).addOrderLine(book3, 1));

        // when
        List<Product> mostPopularProducts = ordersAnalyzer.findThreeMostPopularProducts(orders.stream());

        // then
        assertThat(mostPopularProducts).hasSize(3);
        assertThat(mostPopularProducts).extracting("name").containsExactly("Book 1", "Book 2", "Book 3");
    }

    @Test
    public void shouldReturnMostValuableCustomer() {
        //given
        List<Order> orders = new ArrayList<>();
        Customer customer1 = new Customer(randomId(), "John", "Smith");
        Customer customer2 = new Customer(randomId(), "Ben", "Marshall");
        Product book1 = new Product("Book 1", new BigDecimal("23.00"));
        Product book2 = new Product("Book 2", new BigDecimal("44.00"));
        Product book3 = new Product("Book 3", new BigDecimal("99.00"));
        orders.add(new Order(randomId(), customer1).addOrderLine(book1, 1));
        orders.add(new Order(randomId(), customer1).addOrderLine(book2, 1));
        orders.add(new Order(randomId(), customer2).addOrderLine(book3, 1));

        // when
        Optional<Customer> mostValuableCustomer = ordersAnalyzer.findMostValuableCustomer(orders.stream());

        // then
        assertThat(mostValuableCustomer.isPresent());
        assertThat(mostValuableCustomer.get().getFirstName()).isEqualTo("Ben");
        assertThat(mostValuableCustomer.get().getLastName()).isEqualTo("Marshall");
    }



    private static String randomId() {
        return UUID.randomUUID().toString();
    }

}
