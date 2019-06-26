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


        double merchanseAmount = merchandise.getMerchandise().get("Chocolate eclair");
//        cashRegister.getCashRegisterDenominations().remove(10.00);
        double customerMoney = 20;


        try{
            Double change = transactionManager.conductTransaction(customerMoney, merchanseAmount);
            ArrayList<Double> changeInDenominations = transactionManager.getChangeInDenominations(change);
            System.out.println(change);
            System.out.println(changeInDenominations);
        }
        catch (Exception message){
            System.err.println(message.getMessage());
        }

    }
}
