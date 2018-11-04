package project.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import project.item.dao.ItemDaoImpl;
import project.item.model.Item;
import project.order.model.Order;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Primary
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM \"ORDER\"";

        return template.query(sql, (rs, rowNum) -> {
            Long orderId = rs.getLong("order_id");

            return new Order(
                    orderId,
                    rs.getString("order_number"),
                    new ItemDaoImpl(template).findByOrderId(orderId)
            );
        });
    }

    @Override
    public Order findById(Long id) {
        String sql = "SELECT * FROM \"ORDER\" WHERE order_id = ?";

        try {
            return template.queryForObject(sql, new Object[] {id}, (rs, rowNum) -> {
                Long orderId = rs.getLong("order_id");

                return new Order(
                        orderId,
                        rs.getString("order_number"),
                        new ItemDaoImpl(template).findByOrderId(orderId));
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Order save(Order order) {
        String sql = "INSERT INTO \"ORDER\"(order_id, order_number) VALUES (next value for seq1, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, new String[] {"order_id"});
            ps.setString(1, order.getOrderNumber());

            return ps;
        }, holder);

        if (order.getOrderRows() != null) {
            ItemDaoImpl itemDao = new ItemDaoImpl(template);
            for (Item item : order.getOrderRows()) {
                item.setOrderId(Objects.requireNonNull(holder.getKey()).longValue());
                itemDao.save(item);
            }
        }

        order.setId(Objects.requireNonNull(holder.getKey()).longValue());

        return order;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM \"ORDER\"";

        template.update(sql);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM \"ORDER\" WHERE order_id = ?";

        template.update(sql, id);
    }
}
