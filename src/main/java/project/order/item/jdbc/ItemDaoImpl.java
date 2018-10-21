package project.order.item.jdbc;

import project.order.item.model.Item;
import project.order.utils.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private DataSource dataSource;

    public ItemDaoImpl() {
        this.dataSource = DataSourceProvider.getDataSource();
    }

    @Override
    public List<Item> getItemsByOrderId(Long orderPrimaryKey) {
        String sql = "SELECT * FROM \"ITEM\" WHERE order_id = ?";

        List<Item> items = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, orderPrimaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                items.add(new Item(resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    @Override
    public void insertItem(List<Item> items, Long orderPrimaryKey) {
        String sql = "INSERT INTO \"ITEM\"(name, quantity, price, order_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (Item item : items) {
                preparedStatement.setString(1, item.getItemName());
                preparedStatement.setInt(2, item.getQuantity());
                preparedStatement.setFloat(3, item.getPrice());
                preparedStatement.setFloat(4, orderPrimaryKey);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
