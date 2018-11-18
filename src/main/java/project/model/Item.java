package project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(name = "order_rows")
public class Item {

    @Column(name = "item_name")
    private String itemName;

    @NotNull
    @Min(value = 1, message = "Quantity must be more than 0")
    private Integer quantity;

    @NotNull
    @Min(value = 0, message = "Price must be more than 0")
    private Integer price;
}
