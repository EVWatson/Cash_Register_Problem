import java.lang.reflect.Array;
import java.util.*;

public class TransactionManager {

    private CashRegisterCalculator cashRegisterCalculator;



    public TransactionManager(CashRegisterCalculator cashRegisterCalculator){
        this.cashRegisterCalculator = cashRegisterCalculator;

    }

    public Double conductTransaction(double customerFunds, double merchandiseValue) throws InsufficientCashRegisterFundsException, InsufficientCustomerPaymentException{
        double changeValue = 0.00;

        if(customerFunds == merchandiseValue){
            System.out.println("no change needed");
        }
        if(customerFunds < merchandiseValue){
            throw new InsufficientCustomerPaymentException("Unable to process transaction; insufficient customer payment.");
        }
        if(customerFunds > merchandiseValue){
            changeValue = customerFunds - merchandiseValue;
            if(changeValue > cashRegisterCalculator.getCashRegisterBalance()){
            throw new InsufficientCashRegisterFundsException("Unable to provide change");
            }
        }
        return changeValue;
    }


//    public ArrayList<Double> getChangeInDenominations(Double requiredChange){
//        ArrayList<Double> changeInDenominations = new ArrayList<>();
//
//        if(cashRegisterCalculator.getCashRegisterDenominations().containsKey(requiredChange) && )
//
//    }


    private double getNextDenomination(int currentDenomination){
        List<Double> denominationKeys = new ArrayList<>(cashRegisterCalculator.getCashRegisterDenominations().keySet());
        Collections.sort(denominationKeys);
//        int denomination = currentDenomination.intValue();
        Double nextDenomination = 0.00;

       for(int denomination = currentDenomination; denomination <= currentDenomination +1; denomination ++ ){
           nextDenomination = denominationKeys.get(currentDenomination -1);
       }
       return nextDenomination;
    }


}
