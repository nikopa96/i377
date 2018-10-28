package project.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import project.order.jdbc.ReportDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/orders/report")
public class OrderReportController extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json");
        ReportDaoImpl reportDao = new ReportDaoImpl();

        try {
            String responseJson = objectMapper.writeValueAsString(reportDao.getReport());
            response.getWriter().print(responseJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
