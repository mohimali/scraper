package exception;

public class ProductDescriptionNotFoundException extends ComponentException {

    private static final String productDescriptionNotFoundMessage = "Product description could be Found. See html below \n ";

    public ProductDescriptionNotFoundException(String htmlResponse) {
        super(productDescriptionNotFoundMessage + htmlResponse);
    }
}
