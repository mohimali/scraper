package components;

import api.Scraper;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import exception.GroceriesSearchPageEmptyException;

import java.util.List;

class GroceriesSearchPageComponent extends Scraper {

    private String productsHyperlinksXpath = "//li[@class='gridItem'] //div[@class='productNameAndPromotions']//a";

    List<HtmlAnchor> findProductsHyperlinks() throws GroceriesSearchPageEmptyException {
        List<HtmlAnchor> productsHtmlList = findHyperlinksBasedOnXpath(productsHyperlinksXpath);

        if (productsHtmlList.isEmpty()) {
            throw new GroceriesSearchPageEmptyException(getPage().asXml());
        }
        return productsHtmlList;

    }
}
