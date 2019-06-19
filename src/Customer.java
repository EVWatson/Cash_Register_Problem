import java.util.HashMap;
import java.util.Map;

public class Customer {

    private HashMap<Double, Integer> customerWallet;
    private int totalCustomerFunds;
    private int shoppingTrolley;

    public Customer(){
        this.customerWallet = createCustomerWallet();
        this.totalCustomerFunds = getTotalAvailableFunds();
    }

    public HashMap<Double, Integer> createCustomerWallet(){

        this.customerWallet.put(0.05, 0);
        this.customerWallet.put(0.10, 10);
        this.customerWallet.put(0.20, 5);
        this.customerWallet.put(0.50, 2);
        this.customerWallet.put(1.00, 5);
        this.customerWallet.put(2.00, 1);
        this.customerWallet.put(5.00, 2);
        this.customerWallet.put(10.00, 1);
        this.customerWallet.put(20.00, 3);
        this.customerWallet.put(50.00, 1);

        return this.customerWallet;
    }

    private int getTotalAvailableFunds(){
        int valueInDollars = 0;
        for (Map.Entry<Double, Integer> denomination : this.customerWallet.entrySet() ){
            valueInDollars += denomination.getValue() * denomination.getKey();
        }
        return valueInDollars;

    }

    public int getTotalCustomerFunds() {
        return totalCustomerFunds;
    }

}
