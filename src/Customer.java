import java.util.*;

public class Customer {

    private HashMap<Double, Integer> customerWalletDenominations;
    private int customerWalletBalance;
    private List<Double> customerWalletDenominationKeys;

    public Customer(){
        this.customerWalletDenominations = createCustomerWallet();
        this.customerWalletBalance = getTotalAvailableFunds();
        this.customerWalletDenominationKeys = sortCustomerWalletKeys();
    }

    public HashMap<Double, Integer> createCustomerWallet(){

        this.customerWalletDenominations.put(0.05, 0);
        this.customerWalletDenominations.put(0.10, 10);
        this.customerWalletDenominations.put(0.20, 5);
        this.customerWalletDenominations.put(0.50, 2);
        this.customerWalletDenominations.put(1.00, 5);
        this.customerWalletDenominations.put(2.00, 1);
        this.customerWalletDenominations.put(5.00, 2);
        this.customerWalletDenominations.put(10.00, 1);
        this.customerWalletDenominations.put(20.00, 3);
        this.customerWalletDenominations.put(50.00, 1);

        return this.customerWalletDenominations;
    }

    private int getTotalAvailableFunds(){
        int valueInDollars = 0;
        for (Map.Entry<Double, Integer> denomination : this.getCustomerWalletDenominations().entrySet() ){
            valueInDollars += denomination.getValue() * denomination.getKey();
        }
        return valueInDollars;

    }
    private List<Double> sortCustomerWalletKeys(){
        this.customerWalletDenominationKeys = new ArrayList<>(this.getCustomerWalletDenominations().keySet());
        Collections.sort(this.customerWalletDenominationKeys);
        Collections.reverse(this.customerWalletDenominationKeys);
        return this.customerWalletDenominationKeys;
    }

    public HashMap<Double, Integer> getCustomerWalletDenominations() {
        return customerWalletDenominations;
    }

}
