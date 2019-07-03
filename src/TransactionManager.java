import java.lang.reflect.Array;
import java.util.*;

public class TransactionManager {

    private CashRegisterCalculator cashRegisterCalculator;

    private List<Double> denominationKeys;


    public TransactionManager(CashRegisterCalculator cashRegisterCalculator){
        this.cashRegisterCalculator = cashRegisterCalculator;
        this.denominationKeys = sortCashRegisterKeys();

    }

    public ArrayList<Double> conductTransaction(ArrayList<Double> customerTender, double merchandiseValue) throws InsufficientCashRegisterFundsException, InsufficientCustomerPaymentException{
        double changeValue = 0.00;
        double customerFunds = 0;
        for(Double denomination : customerTender){
            customerFunds += denomination;
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

        ArrayList<Double> returnedChange = getChangeInDenominations(changeValue);
        addToTill(customerTender);
        removeFromTill(returnedChange);
        return returnedChange;
    }


    private ArrayList<Double> getChangeInDenominations(Double requiredChange)throws InsufficientCashRegisterFundsException{
        ArrayList<Double> changeInDenominations = new ArrayList<>();

        for (Double denomination: this.denominationKeys){
            Integer numberOfDenominationAvailable = cashRegisterCalculator.getCashRegisterDenominations().get(denomination);
            Integer numberOfDenominationNeeded = (int) (requiredChange / denomination);

            if( (numberOfDenominationAvailable > 0) ){
                Integer numberOfDenominationNeedingToBeAdded;

                if ( numberOfDenominationAvailable < numberOfDenominationNeeded){
                    numberOfDenominationNeedingToBeAdded = numberOfDenominationAvailable;
                }
                else {
                    numberOfDenominationNeedingToBeAdded = numberOfDenominationNeeded;
                }

                List<Double> denominationsToAdd = Collections.nCopies(numberOfDenominationNeedingToBeAdded, denomination);
                changeInDenominations.addAll(denominationsToAdd);
                requiredChange = requiredChange - (denomination * numberOfDenominationNeedingToBeAdded);

            }
        }
        if(requiredChange == 0){
            return changeInDenominations;
        }
        throw new InsufficientCashRegisterFundsException("Unable to provide change");
    }

    public void removeFromTill(ArrayList<Double> returnedChange){
        for(Double denomination : returnedChange) {
            Integer currentValue = this.cashRegisterCalculator.getCashRegisterDenominations().get(denomination);
            this.cashRegisterCalculator.getCashRegisterDenominations().replace(denomination, currentValue - 1);
        }
    }

    public void addToTill(ArrayList<Double> customerTender){
        for(Double denomination : customerTender) {
            Integer currentValue = this.cashRegisterCalculator.getCashRegisterDenominations().get(denomination);
            this.cashRegisterCalculator.getCashRegisterDenominations().replace(denomination, currentValue + 1);
        }
    }


    private List<Double> sortCashRegisterKeys(){
        this.denominationKeys = new ArrayList<>(cashRegisterCalculator.getCashRegisterDenominations().keySet());
       Collections.sort(this.denominationKeys);
       Collections.reverse(this.denominationKeys);
       return this.denominationKeys;
    }

}
