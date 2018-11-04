package project.item.dao;

import project.item.model.Item;

import java.util.List;

public interface ItemDao {

    public List<Item> findByOrderId(Long orderId);
    public void save(Item item);
}
