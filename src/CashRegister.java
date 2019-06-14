import java.util.HashMap;

public class CashRegister {

    private HashMap<String, Integer> cashRegisterDenominations;
    private int totalCashRegisterFunds;

    public CashRegister(){
        this.cashRegisterDenominations = setUpCashRegister();

    }

    public HashMap<String, Integer> setUpCashRegister(){
        HashMap<String, Integer> cashRegisterFunds = new HashMap<>();
        cashRegisterFunds.put("5cents", 20);
        cashRegisterFunds.put("10cents", 10);
        cashRegisterFunds.put("20cents", 5);
        cashRegisterFunds.put("50cents", 2);
        cashRegisterFunds.put("$1", 10);
        cashRegisterFunds.put("$2", 5);
        cashRegisterFunds.put("$5", 2);
        cashRegisterFunds.put("$10", 10);
        cashRegisterFunds.put("$20", 5);
        cashRegisterFunds.put("$50", 2);

        return cashRegisterFunds;
    }

    public HashMap<String, Integer> getCashRegisterDenominations() {
        return cashRegisterDenominations;
    }
}
