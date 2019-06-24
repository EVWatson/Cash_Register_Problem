import java.lang.reflect.Array;
import java.util.*;

public class TransactionManager {

    private CashRegisterCalculator cashRegisterCalculator;



    public TransactionManager(CashRegisterCalculator cashRegisterCalculator){
        this.cashRegisterCalculator = cashRegisterCalculator;

    }

    public ArrayList<Double> conductTransaction(double customerFunds, double merchandiseValue) throws InsufficientCashRegisterFundsException, InsufficientCustomerPaymentException{
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
        return giveChange(customerFunds, merchandiseValue, changeValue);
    }



//    private double calculateChange(double customerFunds, double merchandiseValue) {
//        double change = 0.00;
//        if(customerFunds > merchandiseValue) {
//
//        }
////        move to different method, such as giveChange
////        if(change > cashRegisterCalculator.getCashRegisterBalance()){
////            throw new InsufficientCashRegisterFundsException("Unable to provide change");
////        }
//        return change;
//    }

    public ArrayList<Double> giveChange(double customerFunds, double merchandiseValue, double requiredChange)  {
        Integer amountOfDenominationInRegister = cashRegisterCalculator.getCashRegisterDenominations().get(requiredChange);
//        Double amountOfDenominationInRegister = cashRegisterCalculator.calculateTotalIndividualDenominationValue(requiredChange);
        boolean requiredChangeIsADenomination = cashRegisterCalculator.getCashRegisterDenominations().containsKey(requiredChange);
        ArrayList<Double> changeInDenominations = new ArrayList<>();
        double changeGivenSoFar = 0.0;
//        eg. required change = 2.00;
//        if required change value exists in the register as a denomination and the value of the denomination is equal to the required change value
        if (requiredChangeIsADenomination && requiredChange <= amountOfDenominationInRegister){
            changeInDenominations.add(requiredChange);
        }
        if (requiredChangeIsADenomination && requiredChange > amountOfDenominationInRegister){
//            can't seem to put index incrementor here. might be a problem later...
            while (changeGivenSoFar <= requiredChange) {
                changeGivenSoFar = requiredChange - amountOfDenominationInRegister;
                changeInDenominations.add(changeGivenSoFar);
                getNextDenomination(cashRegisterCalculator.getCashRegisterDenominations().get(requiredChange));
            }
        }
        return changeInDenominations;
    }


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

//    Set<Double> keySet = cashRegisterCalculator.getCashRegisterDenominations().keySet();
//    ArrayList<Double> keyslist = new ArrayList<>();
//        keyslist.addAll(keySet);
//Collections.sort(keyslist);
//        Collections.reverse(keyslist);
//
//         for(Double key : keyslist){
//        System.out.println(key);
//    }
}
