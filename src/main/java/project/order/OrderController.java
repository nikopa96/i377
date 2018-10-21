package project.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import project.order.item.model.Item;
import project.order.jdbc.OrderDao;
import project.order.jdbc.OrderDaoImpl;
import project.order.model.Order;
import project.order.service.OrderService;
import project.order.utils.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

@WebServlet("/api/orders")
public class OrderController extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestJson = Util.asString(request.getInputStream());

        Order rawOrder = objectMapper.readValue(requestJson, Order.class);
        OrderDao orderDao = new OrderDaoImpl();

        Order responseOrder = orderDao.insertOrder(rawOrder);

        String responseJson = objectMapper.writeValueAsString(responseOrder);

        response.setHeader("Content-Type", "application/json");
        response.getWriter().print(responseJson);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json");

        Optional<String> paramId = Optional.ofNullable(request.getParameter("id"));
        String responseJson;

        if (!paramId.isPresent()) {
            responseJson = objectMapper.writeValueAsString(new OrderDaoImpl().getAllOrders());
        } else {
            responseJson = objectMapper.writeValueAsString(new OrderDaoImpl().getOrderById(Long.parseLong(paramId.get())));
        }

        response.getWriter().print(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.deleteOrder(Long.parseLong(request.getParameter("id")));
    }
}
