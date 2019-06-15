package entity;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TotalTest {

    private Total validTotal;

    @Before
    public void setup(){
        validTotal = Total.builder()
                .gross(new BigDecimal("5.0"))
                .vat(new BigDecimal("0.83"))
                .build();
    }

    @Test
    public void testProductEntityReturnsJsonRepresentation() {
        String expected = "{\"gross\":5.0,\"vat\":0.83}";
        String actual = validTotal.toString();
        Assert.assertEquals("Total does not match expected json", expected, actual);
    }


}
