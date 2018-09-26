package test.hw2;

import test.hw2.model.Order;
import test.hw2.utils.IdGenerator;
import test.hw2.utils.JsonConverter;
import test.hw2.utils.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/orders")
public class HW1 extends HttpServlet {

    private IdGenerator idGenerator = new IdGenerator();
    private JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = Util.asString(request.getInputStream());

        idGenerator.incrementId();

        Order order = jsonConverter.convertJsonToPojo(json);
        order.setId(idGenerator.getId());

        response.setHeader("Content-Type", "application/json");
        response.getWriter().print(jsonConverter.convertPojoToJson(order));
    }
}
