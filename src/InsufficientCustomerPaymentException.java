public class InsufficientCustomerPaymentException extends Exception {

    public InsufficientCustomerPaymentException(String errorMessage) {
        super(errorMessage);
    }
}
