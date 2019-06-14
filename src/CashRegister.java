import java.util.Collection;
import java.util.HashMap;

public class CashRegister {

    public HashMap<String, Integer> setUpCashRegister(){
        HashMap<String, Integer> cashRegisterFunds = new HashMap<>();
        cashRegisterFunds.put("5cents", 10);
        cashRegisterFunds.put("10cents", 5);
        cashRegisterFunds.put("20cents", 5);
        cashRegisterFunds.put("50cents", 10);
        cashRegisterFunds.put("$1", 20);
        cashRegisterFunds.put("$2", 50);
        cashRegisterFunds.put("$5", 100);
        cashRegisterFunds.put("$10", 100);
        cashRegisterFunds.put("$20", 200);
        cashRegisterFunds.put("$50", 300);

        return cashRegisterFunds;
    }


    public boolean areCustomerFundsSufficient(int customerFunds, int merchandiseValue) {
        boolean result = false;
        if(customerFunds >= merchandiseValue){
            result = true;
        }
        return result;
    }

    public boolean doesCustomerRequireChange(int customerFunds, int merchandiseValue) {
        boolean result = false;
        if(customerFunds > merchandiseValue){
            result = true;
        }
        return  result;
    }

    public boolean areCashRegisterFundsSufficient(int cashRegisterFunds, int changeRequired) {
        boolean result = false;
        if(cashRegisterFunds >= changeRequired){
            result = true;
        }
        return result;
    }

    public int calculateChange(int customerFunds, int merchandiseValue) {
        return customerFunds - merchandiseValue;
    }

    public int calculateTotalCashRegisterFunds(HashMap<String, Integer> cashRegisterContents) {
        int sum =0;
        for(int dollar : cashRegisterContents.values()){
            sum +=dollar;
        }

        return sum;
    }
}
