package project.order.dao;

import project.order.model.Order;

import java.util.List;

public interface OrderDao {

    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    void deleteAll();
    void deleteById(Long id);
}
