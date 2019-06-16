package entity;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTest {

    private Product validProduct;
    private Product productWithoutCalories;

    @Before
    public void setup() {
        validProduct = Product.builder()
                .title("Sainsbury's Strawberries 400g")
                .kCalPer100g(33)
                .unitPrice(new BigDecimal("1.75"))
                .description("by Sainsbury's strawberries")
                .build();

        productWithoutCalories = Product.builder()
                .title("Sainsbury's Strawberries 400g")
                .unitPrice(new BigDecimal("1.75"))
                .description("by Sainsbury's strawberries")
                .build();
    }

    @Test
    public void testProductEntityReturnsJsonRepresentation() {
        String expected = "{\"title\":\"Sainsbury's Strawberries 400g\",\"kcal_per_100g\":33,\"unit_price\":1.75,\"description\":\"by Sainsbury's strawberries\"}";
        String actual = validProduct.toString();
        Assert.assertEquals("Product does not match expected json", expected, actual);
        System.out.println(actual);
    }

    @Test
    public void testProductWithoutCaloriesOmitsKCal() {
        String actual = productWithoutCalories.toString();
        System.out.println(actual);
        Assert.assertThat(actual, CoreMatchers.not(CoreMatchers.containsString("kcal_per_100g")));
    }


}
