import components.WebsiteComponent;
import exception.*;

import java.io.IOException;

public class SainsburysScraper {

    public static void main(String[] args) throws GroceriesSearchPageEmptyException, ProductNullException, ProductTitleNotFoundException, ProductDescriptionNotFoundException, ProductPricePerUnitNotFoundException, IOException {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
        WebsiteComponent websiteComponent = new WebsiteComponent();
        System.out.println(websiteComponent.scrapeResults(url));

    }

}
