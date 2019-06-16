package entity;


import exception.ProductNullException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ResultsTest {

    private Results results;

    @Before
    public void setup() throws ProductNullException {
        results = Results.builder().build();
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


        results.addProduct(product1);
        results.addProduct(product2);
    }

    @Test
    public void testResultsReturnsValidJson() {
        String expected = "{\"results\":[{\"title\":\"Sainsbury's Strawberries 400g\",\"kcal_per_100g\":33,\"unit_price\":1.75,\"description\":\"by Sainsbury's strawberries\"},{\"title\":\"Sainsbury's Blueberries 200g\",\"kcal_per_100g\":56,\"unit_price\":1.75,\"description\":\"by Sainsbury's blueberries\"}],\"total\":{\"gross\":3.50,\"vat\":0.58}}";
        String actual = results.toString();
        System.out.println(results.toString());
        Assert.assertEquals("Results does not match expected json", expected,actual);
    }

    @Test
    public void testResultsGrossIsCalculatedCorrectly() {
        String expected = "\"gross\":3.50";
        String actual = results.toString();
        System.out.println(results.toString());

        Assert.assertThat("Total gross was not calculated correctly", actual, CoreMatchers.containsString(expected));
    }

    @Test
    public void testResultsVatIsCalculatedCorrectly() {
        String expected = "\"vat\":0.58";
        String actual = results.toString();
        System.out.println(results.toString());

        Assert.assertThat("Total gross was not calculated correctly", actual, CoreMatchers.containsString(expected));
    }

    @Test(expected = ProductNullException.class)
    public void testNullProductThrowsException() throws ProductNullException {
       results.addProduct(null);
    }


}
