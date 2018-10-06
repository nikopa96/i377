package project.order.model;

import lombok.Getter;
import lombok.Setter;
import project.order.item.model.Item;

import java.util.List;

@Getter
@Setter
public class Order {

    private Long id;
    private String orderNumber;
    private List<Item> orderRows;
}
