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


        for (Double denomination: this.denominationKeys){
            Integer numberOfDenominationAvailable = cashRegisterCalculator.getCashRegisterDenominations().get(denomination);
            Double numberOfDenominationNeeded = requiredChange/denomination;

            if(denomination > requiredChange){
                continue;
            }
            if(requiredChange.equals(denomination) && numberOfDenominationAvailable > 0){
                changeInDenominations.add(denomination);
            }
//            eg, $10 needed, is equal to denomination, but there are no 10 notes available: check next denomination.
                if( (numberOfDenominationAvailable >= numberOfDenominationNeeded) ){
                    for(int numberOfDenominationToBeAdded = 1; numberOfDenominationToBeAdded <= numberOfDenominationNeeded; numberOfDenominationToBeAdded++){
                        changeInDenominations.add(denomination);
                    }
                    if(requiredChange.equals(calculateChangeGivenSoFar(changeInDenominations))){
                        return changeInDenominations;
                    }
                }
            }
        return changeInDenominations;
    }

    private Double calculateChangeGivenSoFar(ArrayList<Double> changeInDenominations){
        Double total = 0.00;
        for (Double denomination : changeInDenominations){
            total += denomination;
        }
        return total;
    }


    private List<Double> sortCashRegisterKeys(){
        this.denominationKeys = new ArrayList<>(cashRegisterCalculator.getCashRegisterDenominations().keySet());
       Collections.sort(this.denominationKeys);
       Collections.reverse(this.denominationKeys);
       return this.denominationKeys;
    }

}
