import java.util.*;

public class CashRegisterCalculator {

    private CashRegister cashRegister;
    private int cashRegisterBalance;
//    private HashMap<Double, Integer> cashRegisterDenominations;


    public CashRegisterCalculator(CashRegister cashRegister){
        this.cashRegister = cashRegister;
        this.cashRegisterBalance = calculateTotalCashRegisterFunds();
    }



    public int getCashRegisterBalance() {
        return cashRegisterBalance;
    }

    public HashMap<Double, Integer> getCashRegisterDenominations() {
        return this.cashRegister.getCashRegisterFunds();
    }

    private int calculateTotalCashRegisterFunds(){
        int valueInDollars = 0;
        for (Map.Entry<Double, Integer> denomination : this.cashRegister.getCashRegisterFunds().entrySet() ){
            valueInDollars += denomination.getValue() * denomination.getKey();
        }
        return valueInDollars;
    }
}
