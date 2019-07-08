import java.util.HashMap;
import java.util.Map;

public class CashRegister {


    private HashMap<Double, Integer> cashRegisterFunds;

    public CashRegister(){
        this.cashRegisterFunds = new HashMap<>();
        cashRegisterFunds.put(0.05, 0);
        cashRegisterFunds.put(0.10, 0);
        cashRegisterFunds.put(0.20, 0);
        cashRegisterFunds.put(0.50, 0);
        cashRegisterFunds.put(1.00, 0);
        cashRegisterFunds.put(2.00, 0);
        cashRegisterFunds.put(5.00, 0);
        cashRegisterFunds.put(10.00, 0);
        cashRegisterFunds.put(20.00, 0);
        cashRegisterFunds.put(50.00, 0);
        cashRegisterFunds.put(100.00, 0);

        initialiseCashRegister();

//        this.cashRegisterDenominations = initialiseCashRegister();
    }


    public HashMap<Double, Integer> getCashRegisterFunds() {
        return cashRegisterFunds;
    }

    private void initialiseCashRegister(){
        for(Map.Entry<Double, Integer> numberOfDenominations : this.cashRegisterFunds.entrySet()){
            Double denominations = numberOfDenominations.getKey();
            Integer setNumberOfDenominations = 5;
            this.cashRegisterFunds.replace(denominations, setNumberOfDenominations);
        }
    }





//    cashRegisterFunds.put(0.05, 20);
//        cashRegisterFunds.put(0.10, 10);
//        cashRegisterFunds.put(0.20, 5);
//        cashRegisterFunds.put(0.50, 2);
//        cashRegisterFunds.put(1.00, 10);
//        cashRegisterFunds.put(2.00, 5);
//        cashRegisterFunds.put(5.00, 2);
//        cashRegisterFunds.put(10.00, 10);
//        cashRegisterFunds.put(20.00, 5);
//        cashRegisterFunds.put(50.00, 2);
//        cashRegisterFunds.put(100.00, 0);
}
