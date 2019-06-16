#Sainsburys Scraper
This scrapes the Sainsburys Website and extracts product information.
Console Application was made with the following in mind
- Component based achitecture to model the different pages of the website
- Entities to model real life products
- Custom exception classes for error Handling
- Used a library called HtmlUnit to model HTML Elements http://htmlunit.sourceforge.net/

- Test Coverage was done in [Test Coverage Report](src/main/test/resources/testcoverage/index.html)


####Dependencies
- Java 1.8
- Apache Maven 3.6.0 (Used at time of creating the application)


####To Run the Application
```console
mvn exec:java
```


####To Run the Tests
```console
mvn test
```






Example response 
```json
{
  "results": [
    {
      "title": "Sainsbury's Strawberries 400g",
      "kcal_per_100g": 33,
      "unit_price": 1.75,
      "description": "by Sainsbury's strawberries"
    },
    {
      "title": "Sainsbury's Blueberries 200g",
      "kcal_per_100g": 45,
      "unit_price": 1.75,
      "description": "by Sainsbury's blueberries"
    },
    {
      "title": "Sainsbury's Cherry Punnet 200g",
      "kcal_per_100g": 52,
      "unit_price": 1.5,
      "description": "Cherries"
    }

  ],
  "total": {
    "gross": 5.00,
    "vat": 0.83
  }
}
```

#### Future improvements
- HTMLAnchors can't be instantiated so couldn't Mock these easily for the WebComponent Class. 
- Would probably try a different library such as JSoup to see how it compares with HtmlUnit