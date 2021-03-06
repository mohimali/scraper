package exception;

public class ProductTitleNotFoundException extends ComponentException {

    private static final String productTitleNotFoundMessage = "Product title could be Found. See html below \n ";

    public ProductTitleNotFoundException(String htmlResponse) {
        super(productTitleNotFoundMessage + htmlResponse);
    }
}
