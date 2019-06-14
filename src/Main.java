
public class Main {

    public static void main(String[] args) {

        /*
        Enter merchandise value
        Enter customer funds
        Check for sufficient funds
        Check if change is needed, and calculate amount
        Check if register can provide change
        Give change; transaction complete
         */

        Merchandise merchandise = new Merchandise();
        Customer customer = new Customer();
        CashRegister cashRegister = new CashRegister();

        System.out.println(merchandise.getMerchandise().get("Chocolate eclair"));

    }
}
