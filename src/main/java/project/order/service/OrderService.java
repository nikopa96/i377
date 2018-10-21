package project.order.service;

import project.order.jdbc.OrderDaoImpl;
import project.order.model.Order;

import java.util.List;

public class OrderService {

    private OrderDaoImpl orderDaoImpl = new OrderDaoImpl();

    public void addOrder(Order order) {
        orderDaoImpl.insertOrder(order);
    }

    public List<Order> getAllOrders() {
        return orderDaoImpl.getAllOrders();
    }

    public Order getOrdersById(Long id) {
        return orderDaoImpl.getOrderById(id);
    }
}
