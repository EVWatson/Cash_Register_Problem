import java.util.HashMap;
import java.util.LinkedHashMap;

public class CashRegister {

    private HashMap<Double, Integer> cashRegisterDenominations;
    private int totalCashRegisterFunds;

    public CashRegister(){
        this.cashRegisterDenominations = setUpCashRegister();
    }

    public HashMap<Double, Integer> setUpCashRegister(){
        HashMap<Double, Integer> cashRegisterFunds = new HashMap<>();
        cashRegisterFunds.put(0.05, 20);
        cashRegisterFunds.put(0.10, 10);
        cashRegisterFunds.put(0.20, 5);
        cashRegisterFunds.put(0.50, 2);
        cashRegisterFunds.put(1.00, 10);
        cashRegisterFunds.put(2.00, 5);
        cashRegisterFunds.put(5.00, 2);
        cashRegisterFunds.put(10.00, 10);
        cashRegisterFunds.put(20.00, 5);
        cashRegisterFunds.put(50.00, 2);

        return cashRegisterFunds;
    }

    public HashMap<Double, Integer> getCashRegisterDenominations() {
        return cashRegisterDenominations;
    }
}
