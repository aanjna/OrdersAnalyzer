package com.devskiller.orders;

import com.devskiller.orders.model.Customer;
import com.devskiller.orders.model.Order;
import com.devskiller.orders.model.OrderLine;
import com.devskiller.orders.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrdersAnalyzer {

    /**
     * Should return at most three most popular products. Most popular product is the product that have the most occurrences
     * in given orders (ignoring product quantity).
     * If two products have the same popularity, then products should be ordered by name
     *
     * @param orders orders stream
     * @return list with up to three most popular products
     */
    public List<Product> findThreeMostPopularProducts(Stream<Order> orders) {
        // TODO: Implement this method
        Map<Product, Long> productCountMap = orders.flatMap(order -> order.getOrderLines().stream()).collect(Collectors.groupingBy(OrderLine::getProduct, Collectors.counting()));

        return productCountMap.entrySet().stream().sorted((e1, e2) -> {
            int compare = e2.getValue().compareTo(e1.getValue());
            if (compare == 0)
                return e1.getKey().getName().compareTo(e2.getKey().getName());
            return compare;
        }).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());

    }

    /**
     * Should return the most valuable customer, that is the customer that has the highest value of all placed orders.
     * If two customers have the same orders value, then any of them should be returned.
     *
     * @param orders orders stream
     * @return Optional of most valuable customer
     */
    public Optional<Customer> findMostValuableCustomer(Stream<Order> orders) {
        // TODO: Implement this method
        Map<Customer, BigDecimal> customerOrderValueMap = new HashMap<>();

        orders.forEach(order -> {
            BigDecimal orderValue = order.getOrderLines().stream()
                    .map(orderLine -> orderLine.getProduct().getPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            customerOrderValueMap.merge(order.getCustomer(), orderValue, BigDecimal::add);
        });

        return customerOrderValueMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

}
