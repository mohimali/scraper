import com.fasterxml.jackson.databind.ObjectMapper;
import components.WebsiteComponent;
import exception.ComponentException;

import java.io.IOException;

public class SainsburysScraper {

    public static void main(String[] args) throws ComponentException, IOException {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
        WebsiteComponent websiteComponent = new WebsiteComponent();
        String results = websiteComponent.scrapeResults(url);


        // Purely to print JSON in a formatted way for the user
        // The original form will be left as it is in WebsiteComponent
        System.out.println("=====================================================================");
        System.out.println("=====================================================================");
        System.out.println("Results are below \n \n");
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(results, Object.class);
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        System.out.println(prettyJson);

    }

}
