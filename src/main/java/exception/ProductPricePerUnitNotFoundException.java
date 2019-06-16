package exception;

public class ProductPricePerUnitNotFoundException extends ComponentException{

    private static final String productPricePerUnitNotFoundMessage = "Product Price per unit could be Found. See html below \n ";

    public ProductPricePerUnitNotFoundException(String htmlResponse) {
        super(productPricePerUnitNotFoundMessage + htmlResponse);
    }
}
