package project.item.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project.item.model.Item;

import java.sql.PreparedStatement;
import java.util.List;

@Primary
@Repository
public class ItemDaoImpl implements ItemDao {

    private JdbcTemplate template;

    public ItemDaoImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Item> findByOrderId(Long orderId) {
        String sql = "SELECT * FROM \"ITEM\" WHERE order_id = ?";

        return template.query(sql, new Object[] {orderId}, (rs, rowNum) -> new Item(
                rs.getString("name"),
                rs.getInt("quantity"),
                rs.getFloat("price"),
                rs.getLong("order_id")
        ));
    }

    @Override
    public void save(Item item) {
        String sql = "INSERT INTO \"ITEM\"(name, quantity, price, order_id) VALUES (?, ?, ?, ?)";

        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, item.getItemName());
            ps.setInt(2, item.getQuantity());
            ps.setFloat(3, item.getPrice());
            ps.setFloat(4, item.getOrderId());

            return ps;
        });
    }
}
