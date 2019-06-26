import java.util.ArrayList;

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
//        Customer customer = new Customer();
        CashRegister cashRegister = new CashRegister();
        CashRegisterCalculator cashRegisterCalculator = new CashRegisterCalculator(cashRegister);
        TransactionManager transactionManager = new TransactionManager(cashRegisterCalculator);


        int merchanseAmount = merchandise.getMerchandise().get("Chocolate eclair");
//        cashRegister.getCashRegisterDenominations().remove(10.00);
        int customerMoney = 40;


        try{
            Double change = transactionManager.conductTransaction(customerMoney, merchanseAmount);
            System.out.println(change);
        }
        catch (Exception message){
            System.err.println(message.getMessage());
        }

    }
}
