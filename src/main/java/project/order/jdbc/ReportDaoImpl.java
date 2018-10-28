package project.order.jdbc;

import project.order.model.Report;
import project.order.utils.DataSourceProvider;
import project.order.utils.Util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportDaoImpl {

    private DataSource dataSource;

    public ReportDaoImpl() {
        dataSource = DataSourceProvider.getDataSource();
    }

    public Report getReport() {
        Report report = new Report();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(Util.readFile("report.sql"));

            while (rs.next()) {
                report.setCount(rs.getLong("count_orders"));
                report.setAverageOrderAmount(rs.getLong("average_order_amount"));
                report.setTurnoverWithoutVAT(rs.getLong("turnover_without_vat"));
                report.setTurnoverVAT((long) rs.getFloat("turnover_vat"));
                report.setTurnoverWithVAT((long) rs.getFloat("turnover_with_vat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }
}
