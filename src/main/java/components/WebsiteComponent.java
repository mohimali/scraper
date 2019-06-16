package components;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import entity.Product;
import entity.Results;
import exception.*;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebsiteComponent {
    @Setter
    public GroceriesSearchPageComponent groceriesSearchPageComponent = new GroceriesSearchPageComponent();

    private Results results;

    public String scrapeResults(String url) throws IOException, ComponentException {
        groceriesSearchPageComponent.setUrl(url);
        results = Results.builder().build();

        List<Product> products = getProductsListFromAnchor(groceriesSearchPageComponent.findProductsHyperlinks());
        for (Product product: products) {
            results.addProduct(product);
        }

        return results.toString();

    }

    public List<Product> getProductsListFromAnchor(List<HtmlAnchor> htmlAnchors) throws IOException, ComponentException {
        List<Product> products = new ArrayList<>();
        for (HtmlAnchor productLink : htmlAnchors) {
            ProductPageComponent productPageComponent = new ProductPageComponent();
            productPageComponent.setUrl(productLink.click().getUrl().toString());
            Product product = productPageComponent.getProduct();
            products.add(product);

        }
        return products;
    }

}
