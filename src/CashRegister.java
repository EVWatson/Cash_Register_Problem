public class CashRegister {
    public boolean areCustomerFundsSufficient(int custmerFunds, int merchValue) {
        boolean result = false;
        if(custmerFunds >= merchValue){
            result = true;
        }
        return result;
    }

    public boolean doesCustomerRequireChange(int customerFunds, int merchValue) {
        boolean result = false;
        if(customerFunds > merchValue){
            result = true;
        }
        return  result;
    }
}
