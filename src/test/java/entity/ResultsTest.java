package entity;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResultsTest {

    private Results results;

    @Before
    public void setup(){
        Product product1 =  Product.builder()
                .title("Sainsbury's Strawberries 400g")
                .kCalPer100g(33)
                .unitPrice(new BigDecimal("1.75"))
                .description("by Sainsbury's strawberries")
                .build();

        Product product2 =  Product.builder()
                .title("Sainsbury's Blueberries 200g")
                .kCalPer100g(56)
                .unitPrice(new BigDecimal("1.75"))
                .description("by Sainsbury's blueberries")
                .build();

        Total total = Total.builder()
                .gross(new BigDecimal("5.0"))
                .vat(new BigDecimal("0.83"))
                .build();


        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        results = Results
                .builder()
                .results(products)
                .total(total)
                .build();
    }

    @Test
    public void testResultsReturnsValidJson() {
        String expected = "{\"results\":[{\"title\":\"Sainsbury's Strawberries 400g\",\"kcal_per_100g\":33,\"unit_price\":1.75,\"description\":\"by Sainsbury's strawberries\"},{\"title\":\"Sainsbury's Blueberries 200g\",\"kcal_per_100g\":56,\"unit_price\":1.75,\"description\":\"by Sainsbury's blueberries\"}],\"total\":{\"gross\":5.0,\"vat\":0.83}}";
        String actual = results.toString();
        System.out.println(results.toString());

        Assert.assertEquals("Results does not match expected json", expected,actual);
    }


}
