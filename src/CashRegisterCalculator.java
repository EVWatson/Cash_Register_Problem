import java.util.Collection;
import java.util.HashMap;

import java.util.Map;

public class CashRegisterCalculator {

    private CashRegister cashRegister;

    public CashRegisterCalculator(CashRegister cashRegister){

        this.cashRegister = cashRegister;
    }



    public boolean areCustomerFundsSufficient(int customerFunds, int merchandiseValue) {
        boolean result = false;
        if(customerFunds >= merchandiseValue){
            result = true;
        }
        return result;
    }



    public int calculateChange(int customerFunds, int merchandiseValue) throws InsufficientCashRegisterFundsException{
        int change = 0;
        if(customerFunds > merchandiseValue) {
            change = customerFunds - merchandiseValue;
        }
        if(change > calculateTotalCashRegisterFunds()){
            throw new InsufficientCashRegisterFundsException("Unable to provide change; insufficient funds available");
        }
        return change;
    }


    public int calculateTotalCashRegisterFunds(){
        int valueInDollars = 0;
        for (Map.Entry<String, Integer> denomination : this.cashRegister.getCashRegisterDenominations().entrySet() ){
            switch (denomination.getKey()){
                case "5cents":
                    valueInDollars += denomination.getValue() * 0.05;
                    break;
                case "10cents":
                    valueInDollars += denomination.getValue() * 0.1;
                    break;
                case "20cents":
                    valueInDollars += denomination.getValue() * 0.2;
                    break;
                case "50cents":
                    valueInDollars += denomination.getValue() * 0.5;
                    break;
                case "$1":
                    valueInDollars += denomination.getValue();
                    break;
                case "$2":
                    valueInDollars += denomination.getValue() * 2;
                    break;
                case "$5":
                    valueInDollars += denomination.getValue() * 5;
                    break;
                case "$10":
                    valueInDollars += denomination.getValue() * 10;
                    break;
                case "$20":
                    valueInDollars += denomination.getValue() * 20;
                    break;
                case "$50":
                    valueInDollars += denomination.getValue() * 50;
                    break;

            }
        }
        return valueInDollars;
    }



//    possibly move this logic into a transaction Router/transaction calculator or similar?

//    public TransactionStatus determineTransactionType(int merchandisePrice, int customerPayment) {
//
//        if (areCustomerFundsSufficient(customerPayment, merchandisePrice) && areCashRegisterFundsSufficient(customerPayment, merchandisePrice)) {
//            return TransactionStatus.CAN_PROCEED;
//        }
//        return TransactionStatus.CANNOT_PROCEED;
//    }

    public String adviseTransactionType(TransactionStatus status) {
        String result;
        switch (status){
            case CANNOT_PROCEED:
                result = "Insufficient funds available";
                break;
            default: case CAN_PROCEED:
                result = "Transaction can proceed";
        }
        return result;
    }
}
