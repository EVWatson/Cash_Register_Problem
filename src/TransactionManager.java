import java.lang.reflect.Array;
import java.util.*;

public class TransactionManager {

    private CashRegisterCalculator cashRegisterCalculator;

    private List<Double> denominationKeys;


    public TransactionManager(CashRegisterCalculator cashRegisterCalculator){
        this.cashRegisterCalculator = cashRegisterCalculator;
        this.denominationKeys = sortCashRegisterKeys();

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


    public ArrayList<Double> getChangeInDenominations(Double requiredChange){
        ArrayList<Double> changeInDenominations = new ArrayList<>();
//        number of actual notes/coins available:
        Integer numberOfCurrentDenominationAvailable = cashRegisterCalculator.getCashRegisterDenominations().get(requiredChange);
//        value of notes/coins available:

        Double numberOfDenominationNeeded = requiredChange/requiredChange;

        for (Double denomination: this.denominationKeys){
//            eg. $10 needed, $10 is a denomination, there are enough $10 notes available to give $10 change
            if(requiredChange.equals(denomination) && numberOfCurrentDenominationAvailable > 0){
                changeInDenominations.add(denomination);
            }
//            eg, $10 needed, is equal to denomination, but there are no 10 notes available: check next denomination.
            if(requiredChange.equals(denomination) && numberOfCurrentDenominationAvailable < numberOfDenominationNeeded){
                Double nextDenomination = getNextDenomination(requiredChange);
                Integer numberOfNextDenominationAvailable = cashRegisterCalculator.getCashRegisterDenominations().get(nextDenomination);
                if( (numberOfNextDenominationAvailable.equals(numberOfDenominationNeeded)) ){
                    changeInDenominations.add(denomination);
                }else {
                    getNextDenomination(nextDenomination);
                }
//
//                requiredChange = requiredChange - valueOfCurrentDenominationAvailable;
//                changeInDenominations.add(numberOfCurrentDenominationAvailable * denomination);
            }
        }
        return changeInDenominations;
    }

    private List<Double> sortCashRegisterKeys(){
        this.denominationKeys = new ArrayList<>(cashRegisterCalculator.getCashRegisterDenominations().keySet());
       Collections.sort(this.denominationKeys);
       return this.denominationKeys;
    }


    private double getNextDenomination(double currentDenomination){
        Double nextDenomination = 0.00;

       for(int index = 0; index <= denominationKeys.size(); index ++){
           if(denominationKeys.get(index).equals(currentDenomination)) {
               nextDenomination = this.denominationKeys.get(index -1);
           }
       }
       return nextDenomination;
    }


}
