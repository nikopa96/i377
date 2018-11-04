package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.order.dao.OrderDao;
import project.order.model.Order;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @GetMapping("orders")
    public List<Order> getOrders() {
        return orderDao.findAll();
    }

    @GetMapping("orders/{order_id}")
    public Order getOrderById(@PathVariable("order_id") Long orderId) {
        return orderDao.findById(orderId);
    }

    @PostMapping("orders")
    public Order save(@RequestBody @Valid Order order) {
        orderDao.save(order);

        return order;
    }

    @DeleteMapping("orders/{order_id}")
    public void deleteOrderById(@PathVariable("order_id") Long orderId) {
        orderDao.deleteById(orderId);
    }

    @DeleteMapping("orders")
    public void deleteAllOrders() {
        orderDao.deleteAll();
    }
}
