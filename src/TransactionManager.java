public class TransactionManager {

    private CashRegisterCalculator cashRegisterCalculator;
    private double cashRegisterDenomination;


    public TransactionManager(CashRegisterCalculator cashRegisterCalculator){
        this.cashRegisterCalculator = cashRegisterCalculator;
        double denominationValue = 0.0;
        this.cashRegisterDenomination = cashRegisterCalculator.getCashRegisterDenominations().get(denominationValue);
    }

    public double conductTransaction(double customerFunds, double merchandiseValue) throws InsufficientCashRegisterFundsException, InsufficientCustomerPaymentException{
        double transactionResult = 0.00;
        if(customerFunds >= merchandiseValue){
            transactionResult = calculateChange(customerFunds, merchandiseValue);
        }
        if(customerFunds<merchandiseValue){
            throw new InsufficientCustomerPaymentException("Unable to process transaction; insufficient customer payment.");
        }
        return transactionResult;
    }



    private double calculateChange(double customerFunds, double merchandiseValue) throws InsufficientCashRegisterFundsException{
        double change = 0.00;
        if(customerFunds > merchandiseValue) {
            change = customerFunds - merchandiseValue;
        }
        if(change > cashRegisterCalculator.getCashRegisterBalance()){
            throw new InsufficientCashRegisterFundsException("Unable to provide change");
        }
        return change;
    }

    public double giveChange(double customerFunds, double merchandiseValue)throws InsufficientCashRegisterFundsException {
        if (this.calculateChange(customerFunds, merchandiseValue) == this.cashRegisterDenomination){
            checkIfSufficient();
        } else {
//            map to next denomination
        }
        else {
            throw new InsufficientCashRegisterFundsException("Unable to provide change");
        }
    }

    public void checkIfSufficient(double customerFunds, double merchandiseValue) throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
        double requiredChange = this.calculateChange(customerFunds, merchandiseValue);
        double cashRegisterDenominationValue = this.cashRegisterCalculator.getCashRegisterDenominations().get(requiredChange).doubleValue();
        if(requiredChange == cashRegisterDenominationValue){
            System.out.println("Change given");
        }
        if(requiredChange > cashRegisterDenominationValue){
            requiredChange = requiredChange - cashRegisterDenominationValue;
//            move to next denomination
        }
        if(cashRegisterDenominationValue == 0){
//            move to next denomination
        }
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
