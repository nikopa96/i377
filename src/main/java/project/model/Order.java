package project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    @NotNull
    @Column(name = "order_number")
    private String orderNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "order_rows",
            joinColumns=@JoinColumn(name = "orders_id",
                    referencedColumnName = "id")
    )
    private List<@Valid Item> orderRows;
}
