package project.order.service;

import project.order.model.Order;
import project.order.repository.OrderRepository;

import java.util.List;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrdersById(Long id) {
        return orderRepository.findById(id);
    }
}
