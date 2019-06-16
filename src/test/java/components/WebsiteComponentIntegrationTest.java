package components;


import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import entity.Product;
import exception.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


/**
 * This test is incomplete
 */
@Ignore
public class WebsiteComponentIntegrationTest {

    private WebsiteComponent websiteComponent;

    private GroceriesSearchPageComponent groceriesSearchPageComponent;
    private Product product1;
    private Product product2;
    private List<Product> products;

    private List<HtmlAnchor> htmlAnchors;


    @Before
    public void setup() {
        websiteComponent = new WebsiteComponent();
        groceriesSearchPageComponent = mock(GroceriesSearchPageComponent.class);
        products = new ArrayList<>();

        product1 = Product.builder()
                .title("Sainsbury's Strawberries 400g")
                .kCalPer100g(33)
                .unitPrice(new BigDecimal("1.75"))
                .description("by Sainsbury's strawberries")
                .build();

        product2 = Product.builder()
                .title("Sainsbury's Blueberies 400g")
                .kCalPer100g(55)
                .unitPrice(new BigDecimal("1.75"))
                .description("by Sainsbury's Blueberies")
                .build();

        products.add(product1);
        products.add(product2);

    }

    @Test
    public void testWebsiteReturnsJsonResults() throws ComponentException, IOException {
        WebsiteComponent websiteComponent = new WebsiteComponent();
        doNothing().when(groceriesSearchPageComponent).setUrl("");
        Mockito.when(websiteComponent.getProductsListFromAnchor(any())).thenReturn(products);

        websiteComponent.setGroceriesSearchPageComponent(groceriesSearchPageComponent);

        // System.out.println(websiteComponent.scrapeResults(""));
    }

}
