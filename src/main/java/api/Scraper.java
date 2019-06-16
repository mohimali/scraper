package api;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public abstract class Scraper {

    private WebClient client;
    @Getter
    private HtmlPage page;

    public void setUrl(String url) throws IOException {
        this.setWebClient(new URL(url), new WebClient());
    }

    public void setWebClient(URL url, WebClient webClient) throws IOException {
        this.client = webClient;

        // Some default options to speed up the page
        this.client.getOptions().setCssEnabled(false);
        this.client.getOptions().setJavaScriptEnabled(false);
        this.client.getOptions().setDownloadImages(false);
        this.page = this.client.getPage(url);
    }

    protected List<HtmlAnchor> findHyperlinksBasedOnXpath(String xpath) {
        return page.getByXPath(xpath);
    }

}
