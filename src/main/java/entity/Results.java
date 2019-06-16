package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ProductNullException;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Builder
public class Results {

    @Getter
    @JsonProperty("results")
    private List<Product> results;

    @Getter
    @JsonProperty("total")
    private Total total;

    @Getter
    @JsonIgnore
    private static final BigDecimal vat = new BigDecimal("1.2");

    public void addProduct(Product product) throws ProductNullException {
        if (results == null) {
            results = new ArrayList<>();
        }

        if (product != null) {
            this.results.add(product);
            this.calculateTotal(vat);
        }
        else{
            throw new ProductNullException();
        }
    }

    private void calculateTotal(BigDecimal vat){
        BigDecimal sum = results.stream()
                .map(Product::getUnitPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal inclusiveVat = sum.subtract(sum.divide(vat,2, RoundingMode.HALF_UP));

        this.total = Total.builder()
                .gross(sum)
                .vat(inclusiveVat)
                .build();

    }
    @Override
    public String toString(){
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
