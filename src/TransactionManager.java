import java.util.*;

public class TransactionManager {

    private CashRegisterCalculator cashRegisterCalculator;



    public TransactionManager(CashRegisterCalculator cashRegisterCalculator){
        this.cashRegisterCalculator = cashRegisterCalculator;

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

    public double giveChange(double customerFunds, double merchandiseValue) throws InsufficientCashRegisterFundsException {
//        if (this.calculateChange(customerFunds, merchandiseValue) == this.cashRegisterCalculator.getCashRegisterDenominations().get()){
        double change = this.calculateChange(customerFunds, merchandiseValue);
        if(this.cashRegisterCalculator.getCashRegisterDenominations().containsKey(change)){
            checkIfSufficient(customerFunds, merchandiseValue, change);
        } else {
//            map to next denomination
        }
        else {
            throw new InsufficientCashRegisterFundsException("Unable to provide change");
        }
    }

    public void checkIfSufficient(double customerFunds, double merchandiseValue, double requiredChange) {
        // get all keys from denominations from register
        Set<Double> keySet = cashRegisterCalculator.getCashRegisterDenominations().keySet();
        //create arraylist from set
        ArrayList<Double> keyslist = new ArrayList<>();
        keyslist.addAll(keySet);
        //sort list
        Collections.sort(keyslist);
        Collections.reverse(keyslist);
        //iterate over all the denominations
        for(Double key : keyslist){
            System.out.println(key);
        }
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
