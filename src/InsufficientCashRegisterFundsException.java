public class InsufficientCashRegisterFundsException extends Exception{
        public InsufficientCashRegisterFundsException(String errorMessage) {
            super(errorMessage + "; insufficient funds available");
        }
    }

