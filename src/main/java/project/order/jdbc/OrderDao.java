package project.order.jdbc;

import project.order.model.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order insertOrder(Order order);
    void deleteAllOrders();
    void deleteOrder(Long id);
}
