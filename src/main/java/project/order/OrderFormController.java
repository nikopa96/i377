package project.order;

import project.order.model.Order;
import project.order.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/orders/form")
public class OrderFormController extends HttpServlet {

    private OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json");

        Optional<String> orderNumber = Optional.ofNullable(request.getParameter("orderNumber"));

        if (orderNumber.isPresent()) {
            Order order = new Order();
            order.setOrderNumber(orderNumber.get());

            orderService.addOrder(order);
            response.getWriter().print(order.getId());
        }
    }
}
