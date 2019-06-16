package components;


import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import entity.Product;
import exception.ProductDescriptionNotFoundException;
import exception.ProductPricePerUnitNotFoundException;
import exception.ProductTitleNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

public class ProductPageComponentTest {

    private ProductPageComponent productPageComponent;

    @Before
    public void setup() {
        productPageComponent = new ProductPageComponent();
    }


    @Test
    public void testGivenProductHtmlReturnsProduct() throws IOException, ProductPricePerUnitNotFoundException, ProductDescriptionNotFoundException, ProductTitleNotFoundException {
        setWebClient(givenProductHtml());
        Product product = productPageComponent.getProduct();

        Assert.assertEquals("Sainsbury's Strawberries 400g", product.getTitle());
        Assert.assertEquals(new BigDecimal("1.75"), product.getUnitPrice());
        Assert.assertEquals("by Sainsbury's strawberries", product.getDescription());
        Assert.assertSame(33, product.getKCalPer100g());
    }


    @Test(expected = ProductPricePerUnitNotFoundException.class)
    public void testGivenProductHtmlWithoutPricePerUnitThrowsException() throws IOException, ProductPricePerUnitNotFoundException, ProductDescriptionNotFoundException, ProductTitleNotFoundException {
        setWebClient(givenProductHtmlNoPricePerUnit());
        productPageComponent.getProduct();
    }

    @Test(expected = ProductTitleNotFoundException.class)
    public void testGivenProductHtmlWithoutTitleThrowsException() throws IOException, ProductPricePerUnitNotFoundException, ProductDescriptionNotFoundException, ProductTitleNotFoundException {
        setWebClient(givenProductHtmlNoTitle());
        productPageComponent.getProduct();
    }

    @Test(expected = ProductDescriptionNotFoundException.class)
    public void testGivenProductHtmlWithoutDescriptionThrowsException() throws IOException, ProductPricePerUnitNotFoundException, ProductDescriptionNotFoundException, ProductTitleNotFoundException {
        setWebClient(givenProductHtmlNoDescription());
        productPageComponent.getProduct();
    }


    private void setWebClient(String htmlResponse) throws IOException {
        Locale.setDefault(Locale.ENGLISH);
        URL startUrl = new URL("http://productPage.com/");
        MockWebConnection webConnection = new MockWebConnection();
        webConnection.setResponse(startUrl, htmlResponse);
        webConnection.setDefaultResponse("<html></html>", "text/html");

        WebClient webClient = new WebClient();
        webClient.setWebConnection(webConnection);
        productPageComponent.setWebClient(startUrl, webClient);
    }

    private String givenProductHtml() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/java/resources/productHtmlValid.html")), StandardCharsets.UTF_8);
    }

    private String givenProductHtmlNoDescription() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/java/resources/productHtmlNoDescription.html")), StandardCharsets.UTF_8);
    }

    private String givenProductHtmlNoTitle() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/java/resources/productHtmlNoTitle.html")), StandardCharsets.UTF_8);
    }

    private String givenProductHtmlNoPricePerUnit() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/java/resources/productHtmlNoPricePerUnit.html")), StandardCharsets.UTF_8);
    }
}
