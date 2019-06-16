package components;


import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import exception.GroceriesSearchPageEmptyException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GroceriesSearchPageComponentTest {

    private GroceriesSearchPageComponent groceriesSearchPageComponent;

    @Before
    public void setup() {
        groceriesSearchPageComponent = new GroceriesSearchPageComponent();
    }

    @Test
    public void testGivenSearchPageWithLinksReturnLinks() throws IOException, GroceriesSearchPageEmptyException {
        setWebClient(givenSearchPageWithLinks());

        List<HtmlAnchor> productsHyperlinks = groceriesSearchPageComponent.findProductsHyperlinks();
        for (HtmlAnchor htmlLinkTag : productsHyperlinks) {
            Assert.assertThat(htmlLinkTag.asXml(), CoreMatchers.containsString("href"));

        }
    }


    @Test(expected = GroceriesSearchPageEmptyException.class)
    public void testGivenSearchPageWithNoLinksThrowException() throws IOException, GroceriesSearchPageEmptyException {
        setWebClient(givenSearchPageHtmlWithNoLinks());
        groceriesSearchPageComponent.findProductsHyperlinks();
    }


    private void setWebClient(String htmlResponse) throws IOException {
        URL startUrl = new URL("http://searchPage.com/");
        MockWebConnection webConnection = new MockWebConnection();
        webConnection.setResponse(startUrl, htmlResponse);
        webConnection.setDefaultResponse("<html></html>", "text/html");

        WebClient webClient = new WebClient();
        webClient.setWebConnection(webConnection);
        groceriesSearchPageComponent.setWebClient(startUrl, webClient);
    }

    private String givenSearchPageHtmlWithNoLinks() {

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "<div class=\"section\" id=\"productsContainer\">\n" +
                "    <li class=\"gridItem\">\n" +
                "        <div class=\"product \">\n" +
                "            <div class=\"productInfo\">\n" +
                "                <div class=\"productNameAndPromotions\">\n" +
                "                    <h3>\n" +
                "                        \n" +
                "                    </h3>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"product \">\n" +
                "            <div class=\"productInfo\">\n" +
                "                <div class=\"productNameAndPromotions\">\n" +
                "                    <h3>\n" +
                "                    </h3>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "        </div>\n" +
                "    </li>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }


    private String givenSearchPageWithLinks() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "<div class=\"section\" id=\"productsContainer\">\n" +
                "    <li class=\"gridItem\">\n" +
                "        <div class=\"product \">\n" +
                "            <div class=\"productInfo\">\n" +
                "                <div class=\"productNameAndPromotions\">\n" +
                "                    <h3>\n" +
                "                        <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html\">\n" +
                "                            Sainsbury's Strawberries 400g\n" +
                "                        </a>\n" +
                "                    </h3>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"product \">\n" +
                "            <div class=\"productInfo\">\n" +
                "                <div class=\"productNameAndPromotions\">\n" +
                "                    <h3>\n" +
                "                        <a href=\"../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-british-blackberries-400g.html\">\n" +
                "                            Sainsbury's Blackberies 400g\n" +
                "                        </a>\n" +
                "                    </h3>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "        </div>\n" +
                "    </li>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }


}
