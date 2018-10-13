package project.order.jdbc;

import project.order.model.Order;
import project.order.utils.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private DataSource dataSource;

    public OrderDao() {
        dataSource = DataSourceProvider.getDataSource();
    }

    public void insertOrder(Order order) {
        String sql = "INSERT INTO \"ORDER\"(id, order_number) VALUES (next value for seq1, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, order.getOrderNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getOrderById(Long id) {
        String sql = "SELECT * FROM \"ORDER\" WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Order(resultSet.getLong("id"),
                        resultSet.getString("order_number"),
                        null);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM \"ORDER\"";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order(resultSet.getLong("id"),
                        resultSet.getString("order_number"),
                        null);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderList;
    }
}
