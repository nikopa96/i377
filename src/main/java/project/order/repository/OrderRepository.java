package project.order.repository;

import project.order.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {

    private static List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
    }

    public List<Order> findAll() {
        return orders;
    }

    public Order findById(Long id) {
        if (!orders.isEmpty() && id <= orders.size()) {
            return orders
                    .stream()
                    .filter(order -> order.getId().equals(id))
                    .collect(Collectors.toList()).get(0);
        } else {
            return null;
        }
    }
}
