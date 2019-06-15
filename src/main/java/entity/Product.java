package entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    @JsonProperty("title")
    private String title;

    @JsonProperty("kcal_per_100g")
    private Integer kCalPer100g;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @JsonProperty("description")
    private String description;

    @Override
    public String toString() {

        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
