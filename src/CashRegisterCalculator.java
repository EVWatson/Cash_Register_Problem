import java.util.*;

public class CashRegisterCalculator {

    private CashRegister cashRegister;
    private int cashRegisterBalance;


    public CashRegisterCalculator(CashRegister cashRegister){
        this.cashRegister = cashRegister;
        this.cashRegisterBalance = calculateTotalCashRegisterFunds();
    }


    public int getCashRegisterBalance() {
        return cashRegisterBalance;
    }

    public HashMap<Double, Integer> getCashRegisterDenominations() {
        return this.cashRegister.getCashRegisterDenominations();
    }

    public void removeFromTill(ArrayList<Double> returnedChange){
        for(Double denomination : returnedChange) {
            Integer currentValue = this.cashRegister.getCashRegisterDenominations().get(denomination);
            this.cashRegister.getCashRegisterDenominations().replace(denomination, currentValue - 1);
        }
    }

    private int calculateTotalCashRegisterFunds(){
        int valueInDollars = 0;
        for (Map.Entry<Double, Integer> denomination : this.cashRegister.getCashRegisterDenominations().entrySet() ){
            valueInDollars += denomination.getValue() * denomination.getKey();
        }
        return valueInDollars;
    }
}
