package project.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Report {

    private Long count;
    private Long averageOrderAmount;
    private Long turnoverWithoutVAT;
    private Long turnoverVAT;
    private Long turnoverWithVAT;
}
