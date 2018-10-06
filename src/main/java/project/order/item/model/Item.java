package project.order.item.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {

    private String itemName;
    private Integer quantity;
    private Float price;
}
