import java.util.*;

public class CashRegisterCalculator {

    private CashRegister cashRegister;
    private int cashRegisterBalance;


    public CashRegisterCalculator(CashRegister cashRegister){
        this.cashRegister = cashRegister;
        this.cashRegisterBalance = calculateTotalCashRegisterFunds();
    }


    private int calculateTotalCashRegisterFunds(){
        int valueInDollars = 0;
        for (Map.Entry<Double, Integer> denomination : this.cashRegister.getCashRegisterDenominations().entrySet() ){
            valueInDollars += denomination.getValue() * denomination.getKey();
        }
        return valueInDollars;
    }

    public Double calculateTotalIndividualDenominationValue(Double key){
       return this.getCashRegisterDenominations().get(key) * key;
    }

//    public Double getNumberOfDenominationRequiredForValue(Double Double key){
//        return this.getCashRegisterDenominations().get(key);
//    }

    public int getCashRegisterBalance() {
        return cashRegisterBalance;
    }

    public HashMap<Double, Integer> getCashRegisterDenominations() {
        return this.cashRegister.getCashRegisterDenominations();
    }
}
