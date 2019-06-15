package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Results {

    private List<Product> products;
    private Total total;

}
