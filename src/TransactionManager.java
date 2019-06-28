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
            return changeValue;
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


    public ArrayList<Double> getChangeInDenominations(Double requiredChange)throws InsufficientCashRegisterFundsException{
        ArrayList<Double> changeInDenominations = new ArrayList<>();

        for (Double denomination: this.denominationKeys){
            Integer numberOfDenominationAvailable = cashRegisterCalculator.getCashRegisterDenominations().get(denomination);
            Integer numberOfDenominationNeeded = (int) (requiredChange / denomination);

            if(denomination > requiredChange){
                continue;
            }
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

                if(requiredChange == 0){
                    return changeInDenominations;
                }
            }
        }
        throw new InsufficientCashRegisterFundsException("Unable to provide change");
    }


    private List<Double> sortCashRegisterKeys(){
        this.denominationKeys = new ArrayList<>(cashRegisterCalculator.getCashRegisterDenominations().keySet());
       Collections.sort(this.denominationKeys);
       Collections.reverse(this.denominationKeys);
       return this.denominationKeys;
    }

}
