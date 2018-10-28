package project.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import project.error.ValidationError;
import project.error.ValidationErrors;
import project.order.jdbc.OrderDao;
import project.order.jdbc.OrderDaoImpl;
import project.order.model.Order;
import project.order.utils.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@WebServlet("/api/orders")
public class OrderController extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    private ValidationErrors validationErrors = new ValidationErrors();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json");

        String requestJson = Util.asString(request.getInputStream());
        Order rawOrder = objectMapper.readValue(requestJson, Order.class);

        if (rawOrder.getOrderNumber().length() >= 2) {
            OrderDao orderDao = new OrderDaoImpl();

            Order responseOrder = orderDao.insertOrder(rawOrder);
            String responseJson = objectMapper.writeValueAsString(responseOrder);

            response.getWriter().print(responseJson);
        } else {
            validationErrors.setErrors(Collections.singletonList(new ValidationError("too_short_number")));
            String responseJson = objectMapper.writeValueAsString(validationErrors);

            response.getWriter().print(responseJson);
            response.setStatus(400);
        }
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
        Optional<String> paramId = Optional.ofNullable(request.getParameter("id"));

        if (paramId.isPresent()) {
            orderDao.deleteOrder(Long.parseLong(request.getParameter("id")));
        } else {
            orderDao.deleteAllOrders();
        }
    }
}
