public class TransactionManager {

    private CashRegisterCalculator cashRegisterCalculator;

    public TransactionManager(CashRegisterCalculator cashRegisterCalculator){
        this.cashRegisterCalculator = cashRegisterCalculator;
    }

    public int conductTransaction(int customerFunds, int merchandiseValue) throws InsufficientCashRegisterFundsException, InsufficientCustomerPaymentException{
        int transactionResult = 0;
        if(customerFunds >= merchandiseValue){
            transactionResult = calculateChange(customerFunds, merchandiseValue);
        }
        if(customerFunds<merchandiseValue){
            throw new InsufficientCustomerPaymentException("Unable to process transaction; insufficient customer payment.");
        }
        return transactionResult;
    }



    private int calculateChange(int customerFunds, int merchandiseValue) throws InsufficientCashRegisterFundsException{
        int change = 0;
        if(customerFunds > merchandiseValue) {
            change = customerFunds - merchandiseValue;
        }
        if(change > cashRegisterCalculator.getCashRegisterBalance()){
            throw new InsufficientCashRegisterFundsException("Unable to provide change");
        }
        return change;
    }


//    public String transactionOutcome(TransactionStatus status) {
//        String result;
//        switch (status){
//            case CANNOT_PROCEED:
//                result = "Insufficient funds available";
//                break;
//            default: case SUCCESSFUL:
//                result = "Transaction complete";
//        }
//        return result;
//    }
}
