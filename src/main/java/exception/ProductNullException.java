package exception;

public class ProductNullException extends Exception {

    private static final String NULL_PRODUCT_MESSAGE = "Null Product cannnot be added";

    public ProductNullException(){
        super(NULL_PRODUCT_MESSAGE);
    }
}
