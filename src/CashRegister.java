public class CashRegister {
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
}
