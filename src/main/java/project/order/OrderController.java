package project.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import project.order.model.Order;
import project.order.service.OrderService;
import project.order.utils.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/api/orders")
public class OrderController extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestJson = Util.asString(request.getInputStream());

        Order order = objectMapper.readValue(requestJson, Order.class);
        orderService.addOrder(order);

        String responseJson = objectMapper.writeValueAsString(order);

        response.setHeader("Content-Type", "application/json");
        response.getWriter().print(responseJson);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json");

        Optional<String> paramId = Optional.ofNullable(request.getParameter("id"));
        String responseJson;

        if (!paramId.isPresent()) {
            responseJson = objectMapper.writeValueAsString(orderService.getAllOrders());
        } else {
            responseJson = objectMapper.writeValueAsString(orderService.getOrdersById(Long.parseLong(paramId.get())));
        }

        response.getWriter().print(responseJson);
    }
}
