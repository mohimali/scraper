package components;

import api.Scraper;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import entity.Product;
import exception.ProductDescriptionNotFoundException;
import exception.ProductPricePerUnitNotFoundException;
import exception.ProductTitleNotFoundException;

import java.math.BigDecimal;

public class ProductPageComponent extends Scraper {

    private static final String productPageTitleXpath = ".//div[@class='productSummary'] /div[@class='productTitleDescriptionContainer'] /h1";
    private static final String productPagePerUnitXpath = ".//div[@class='productSummary'] //p[@class='pricePerUnit']";
    private static final String productPageDescriptionXpath = ".//div[@class='mainProductInfo'] //div[@class='section'] //p[normalize-space()]";
    private static final String productPageCaloriesXpath1 = ".//table[@class='nutritionTable'] //tr[@class='tableRow0'] /td";
    private static final String productPageCaloriesXpath2 = ".//table[@class='nutritionTable'] //tr[2] //td";
    private static final String regExToExtractNumbers = "[^.0123456789]";

    public Product getProduct() throws ProductTitleNotFoundException, ProductDescriptionNotFoundException, ProductPricePerUnitNotFoundException {

        HtmlHeading1 productTitleAnchor = this.getPage().getFirstByXPath(productPageTitleXpath);
        HtmlParagraph productPagePricePerUnit = this.getPage().getFirstByXPath(productPagePerUnitXpath);
        HtmlParagraph productPageDescription = this.getPage().getFirstByXPath(productPageDescriptionXpath);

        if (productTitleAnchor == null) {
            throw new ProductTitleNotFoundException(this.getPage().asXml());
        }

        if (productPagePricePerUnit == null) {
            throw new ProductPricePerUnitNotFoundException(this.getPage().asXml());
        }

        if (productPageDescription == null) {
            throw new ProductDescriptionNotFoundException(this.getPage().asXml());
        }

        HtmlTableDataCell productPageCalories = this.getPage().getFirstByXPath(productPageCaloriesXpath1);
        if (productPageCalories == null) {
            // Try second match
            productPageCalories = this.getPage().getFirstByXPath(productPageCaloriesXpath2);
        }


        String title = productTitleAnchor.asText();
        String pricePerUnit = productPagePricePerUnit.asText().replaceAll(regExToExtractNumbers, "");
        String description = productPageDescription.asText();

        String calories = null;
        if (productPageCalories != null) {
            calories = productPageCalories.asText().replaceAll(regExToExtractNumbers, "");
        }

        Integer caloriesInteger = calories == null ? null : Integer.valueOf(calories);

        return Product.builder()
                .title(title)
                .unitPrice(new BigDecimal(pricePerUnit))
                .kCalPer100g(caloriesInteger)
                .description(description)
                .build();

    }
}
