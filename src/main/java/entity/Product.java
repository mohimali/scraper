package entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private String title;
    private Integer kCalPer100g;
    private BigDecimal unitPrice;
    private String description;

}
