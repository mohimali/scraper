package exception;

public class GroceriesSearchPageEmptyException extends ComponentException {

    private static final String emptyListMessage = "No Links could be Found. See html below \n ";

    public GroceriesSearchPageEmptyException(String htmlResponse){
        super(emptyListMessage + htmlResponse);
    }
}
