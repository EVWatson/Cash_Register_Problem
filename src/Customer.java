import java.util.HashMap;
import java.util.Map;

public class Customer {

    private HashMap<String, Integer> customerWallet;
    private int shoppingTrolley;

//    public Customer(){
//        this.customerWallet = createCustomerWallet();
//    }
//
//    public HashMap<String, Integer> createCustomerWallet(){
////        HashMap<String, Integer> customerWallet = new HashMap<>();
//
//        this.customerWallet.put("5cents", 0);
//        this.customerWallet.put("10cents", 10);
//        this.customerWallet.put("20cents", 5);
//        this.customerWallet.put("50cents", 2);
//        this.customerWallet.put("$1", 5);
//        this.customerWallet.put("$2", 1);
//        this.customerWallet.put("$5", 2);
//        this.customerWallet.put("$10", 1);
//        this.customerWallet.put("$20", 3);
//        this.customerWallet.put("$50", 1);
//
//        return this.customerWallet;
//    }
//
//    public HashMap<String, Integer> getCustomerWallet() {
//        return customerWallet;
//    }
//
//    public int getTotalAvailableFunds(){
//        int valueInDollars = 0;
//        for (Map.Entry<String, Integer> denomination : this.customerWallet.entrySet() ){
//            switch (denomination.getKey()){
//                case "5cents":
//                    valueInDollars += denomination.getValue() * 0.05;
//                    break;
//                case "10cents":
//                    valueInDollars += denomination.getValue() * 0.1;
//                    break;
//                case "20cents":
//                    valueInDollars += denomination.getValue() * 0.2;
//                    break;
//                case "50cents":
//                    valueInDollars += denomination.getValue() * 0.5;
//                    break;
//                case "$1":
//                    valueInDollars += denomination.getValue();
//                    break;
//                case "$2":
//                    valueInDollars += denomination.getValue() * 2;
//                    break;
//                case "$5":
//                    valueInDollars += denomination.getValue() * 5;
//                    break;
//                case "$10":
//                    valueInDollars += denomination.getValue() * 10;
//                    break;
//                case "$20":
//                    valueInDollars += denomination.getValue() * 20;
//                    break;
//                case "$50":
//                    valueInDollars += denomination.getValue() * 50;
//                    break;
//
//            }
//        }
//        return valueInDollars;
//
//    }
//
//    public int makePayment(){
//      return getTotalAvailableFunds();
//    }
}
