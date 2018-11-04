package project.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String itemName;

    @NotNull
    @Min(value = 1, message = "Quantity must be more than 0")
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0.01", message = "Price must be more than 0.01")
    private Float price;

    private Long orderId;
}
