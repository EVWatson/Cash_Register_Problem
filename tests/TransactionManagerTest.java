import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        double product = merchandise.getMerchandise().get("Whole gateau");
        double customerPayment = 50;

        double expectedChange = 20;

        double actualChange = 0.00;

        try {
            actualChange = transactionManager.conductTransaction(customerPayment, product);
        }
        catch (InsufficientCustomerPaymentException message) {
            message.getMessage();
        }

        assertEquals(expectedChange, actualChange, 0);
    }

    @Test
    public void whenFundsGivenAreLessThanMerchandiseValueExceptionIsThrown() throws InsufficientCashRegisterFundsException{
        double merchValue = 100;
        double customerFunds = 99;

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
        double merchValue = 100;
        double customerFunds = 100;

        double expectedChange = 0;
        double actualChange = 0;

        try {
            actualChange = transactionManager.conductTransaction(customerFunds, merchValue);
        }
        catch (InsufficientCustomerPaymentException message) {
            message.getMessage();
        }

        assertEquals(expectedChange, actualChange, 0);
    }

    @Test
    public void whenCashRegisterContainsSufficientFundsChangeCanBeProvided() throws InsufficientCustomerPaymentException{
        double customerPayment = 50;
        double merchandisePrice = merchandise.getMerchandise().get("Chocolate eclair");

        double expectedChange = 40;
        double actualChange = 0;

        try {
            actualChange = transactionManager.conductTransaction(customerPayment, merchandisePrice);
        }
        catch (InsufficientCashRegisterFundsException message){
            message.getMessage();
        }

        assertEquals(expectedChange, actualChange, 0);

    }

    @Test
    public void whenCashRegisterDoesNotContainSufficientFundsToProvideChangeExceptionIsThrown()throws InsufficientCustomerPaymentException {
        int customerPayment = 5000;
        Double merchandisePrice = merchandise.getMerchandise().get("Chocolate eclair");

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
    public void whenRequiredChangeIsEqualToADenominationThatIsAvailableAndSufficientChangeIsGivenInThatDenomination() {

        ArrayList<Double> expectedChangeDenomination = new ArrayList<>();
        expectedChangeDenomination.add(10.00);

        ArrayList<Double> actualChangeDenomination = (transactionManager.getChangeInDenominations(10.00));

        assertEquals(expectedChangeDenomination,actualChangeDenomination);
    }

    @Test
    public void whenRequiredChangeIsEqualToADenominationThatIsUnavailableNextDenominationDownIsUsedToMakeChange(){

        this.cashRegisterCalculator.getCashRegisterDenominations().remove(10.00);

        ArrayList<Double> expectedChangeDenomination = new ArrayList<>();
        expectedChangeDenomination.add(5.00);
        expectedChangeDenomination.add(5.00);
        ArrayList<Double> actualChangeDenomination = transactionManager.getChangeInDenominations(10.00);

        assertEquals(expectedChangeDenomination,actualChangeDenomination);
    }

    @Test
    public void whenChangeCannotBeGivenWithASingleDenominationMultipleDenominationsAreUsed(){

    }

    @Test
    public void whenChangeRequiredCannotBeMadeFromAvailableCashRegisterDenominationsThrowsException(){

    }


}