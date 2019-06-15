package entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Total {
    private BigDecimal gross;
    private BigDecimal vat;
}
