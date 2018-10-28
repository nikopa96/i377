package project.order.jdbc;

import project.order.item.jdbc.ItemDaoImpl;
import project.order.item.model.Item;
import project.order.model.Order;
import project.order.utils.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private DataSource dataSource;

    public OrderDaoImpl() {
        dataSource = DataSourceProvider.getDataSource();
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM \"ORDER\"";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                List<Item> items = new ItemDaoImpl().getItemsByOrderId(resultSet.getLong("order_id"));
                Order order = new Order(resultSet.getLong("order_id"),
                        resultSet.getString("order_number"),
                        items);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderList;
    }

    @Override
    public Order getOrderById(Long id) {
        String sql = "SELECT * FROM \"ORDER\" WHERE order_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                List<Item> items = new ItemDaoImpl().getItemsByOrderId(resultSet.getLong("order_id"));

                return new Order(resultSet.getLong("order_id"),
                        resultSet.getString("order_number"),
                        items);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order insertOrder(Order order) {
        String sql = "INSERT INTO \"ORDER\"(order_id, order_number) VALUES (next value for seq1, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql,
                     new String[]{"order_id", "order_number"})) {

            preparedStatement.setString(1, order.getOrderNumber());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                if (order.getOrderRows() != null) {
                    new ItemDaoImpl().insertItem(order.getOrderRows(), resultSet.getLong(1));

                    return new Order(resultSet.getLong(1),
                            resultSet.getString("order_number"),
                            order.getOrderRows());
                } else {
                    return new Order(resultSet.getLong(1),
                            resultSet.getString("order_number"),
                            null);
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllOrders() {
        String sql = "DELETE FROM \"ORDER\"";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        String sql = "DELETE FROM \"ORDER\" WHERE order_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
