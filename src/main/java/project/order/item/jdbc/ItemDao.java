package project.order.item.jdbc;

import project.order.item.model.Item;

import java.util.List;

public interface ItemDao {

    public List<Item> getItemsByOrderId(Long orderPrimaryKey);
    public void insertItem(List<Item> items, Long orderPrimaryKey);
}
