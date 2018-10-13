package project.order.service;

import project.order.jdbc.OrderDao;
import project.order.model.Order;

import java.util.List;

public class OrderService {

    private OrderDao orderDao = new OrderDao();

    public void addOrder(Order order) {
        orderDao.insertOrder(order);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public Order getOrdersById(Long id) {
        return orderDao.getOrderById(id);
    }
}
