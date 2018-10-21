package project.order.jdbc;

import project.order.model.Order;

import java.util.List;

public interface OrderDao {

    public List<Order> getAllOrders();
    public Order getOrderById(Long id);
    public Order insertOrder(Order order);
    public void deleteOrder(Long id);
}
