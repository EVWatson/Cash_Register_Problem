import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionManagerTest {


    private TransactionManager transactionManager;
    private Merchandise merchandise;
    private CashRegisterCalculator cashRegisterCalculator;

    @Before
    public void setUp(){
        CashRegister cashRegister = new CashRegister();
        this.cashRegisterCalculator = new CashRegisterCalculator(cashRegister);
        this. transactionManager = new TransactionManager(cashRegisterCalculator);
        this.merchandise = new Merchandise();
    }

    @Test
    public void whenFundsGivenAreGreaterThanMerchandiseValueChangeOwingIsCalculated() throws InsufficientCashRegisterFundsException{
        int product = merchandise.getMerchandise().get("Whole gateau");
        int customerPayment = 50;

        int expectedChange = 20;

        int actualChange = 0;

        try {
            actualChange = transactionManager.conductTransaction(customerPayment, product);
        }
        catch (InsufficientCustomerPaymentException message) {
            message.getMessage();
        }

        assertEquals(expectedChange, actualChange);
    }

    @Test
    public void whenFundsGivenAreLessThanMerchandiseValueExceptionIsThrown() throws InsufficientCashRegisterFundsException{
        int merchValue = 100;
        int customerFunds = 99;

        String expectedMessage = "Unable to process transaction; insufficient customer payment.";

        String actualMessage = "";
        try {
            transactionManager.conductTransaction(customerFunds, merchValue);
        }
        catch (InsufficientCustomerPaymentException message) {
            actualMessage = message.getMessage();
        }
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenFundsGivenAreEqualToMerchandiseValueCustomerWillNotReceiveAnyChange() throws InsufficientCashRegisterFundsException{
        int merchValue = 100;
        int customerFunds = 100;

        int expectedChange = 0;
        int actualChange = 0;

        try {
            actualChange = transactionManager.conductTransaction(customerFunds, merchValue);
        }
        catch (InsufficientCustomerPaymentException message) {
            message.getMessage();
        }

        assertEquals(expectedChange, actualChange);
    }

    @Test
    public void whenCashRegisterContainsSufficientFundsChangeCanBeProvided() throws InsufficientCustomerPaymentException{
        int customerPayment = 50;
        int merchandisePrice = merchandise.getMerchandise().get("Chocolate eclair");

        int expectedChange = 40;
        int actualChange = 0;

        try {
            actualChange = transactionManager.conductTransaction(customerPayment, merchandisePrice);
        }
        catch (InsufficientCashRegisterFundsException message){
            message.getMessage();
        }

        assertEquals(expectedChange, actualChange);

    }

    @Test
    public void whenCashRegisterDoesNotContainSufficientFundsToProvideChangeExceptionIsThrown()throws InsufficientCustomerPaymentException {
        int customerPayment = 5000;
        int merchandisePrice = merchandise.getMerchandise().get("Chocolate eclair");

        String expectedMessage = "Unable to provide change; insufficient funds available";

        String actualMessage = "";
        try {
            transactionManager.conductTransaction(customerPayment, merchandisePrice);
        }
        catch (InsufficientCashRegisterFundsException message) {
            actualMessage = message.getMessage();
        }

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void whenRequiredChangeIsEqualToADenominationThatIsAvailableAndSufficientChangeIsGivenInThatDenomination() throws InsufficientCashRegisterFundsException, InsufficientCustomerPaymentException{
        int merchandisePrice = merchandise.getMerchandise().get("Whole gateau");
//        gateau = $30
        int customerPayment = 40;
        this.transactionManager.conductTransaction(customerPayment, merchandisePrice);

        double expectedChangeDenomination = 10.00;
        double actualChangeDenomination = transactionManager.giveChange(customerPayment, merchandisePrice);

        assertEquals(expectedChangeDenomination,actualChangeDenomination, 0);
    }

//    @Test
//    public void whenRequiredChangeIsEqualToADenominationThatIsUnavailableNextDenominationDownIsChecked()throws InsufficientCashRegisterFundsException, InsufficientCustomerPaymentException{
//        int merchandisePrice = merchandise.getMerchandise().get("Whole gateau");
////        gateau = $30
//        int customerPayment = 40;
//        this.cashRegisterCalculator.getCashRegisterDenominations().remove(10.00);
//        this.transactionManager.conductTransaction(customerPayment, merchandisePrice);
//
//        double expectedChangeDenomination = 5.00 + 5.00;
//        double actualChangeDenomination = transactionManager.giveChange(customerPayment, merchandisePrice);
//
//        assertEquals(expectedChangeDenomination,actualChangeDenomination, 0);
//    }

    @Test
    public void whenChangeCannotBeGivenWithASingleDenominationMultipleDenominationsAreUsed(){

    }

    @Test
    public void whenChangeRequiredCannotBeMadeFromAvailableCashRegisterDenominationsThrowsException(){

    }


}