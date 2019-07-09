import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TransactionManagerTest {


    private TransactionManager transactionManager;
    private CashRegisterCalculator cashRegisterCalculator;
    private ArrayList<Double> tender;

    @Before
    public void setUp(){
        CashRegister cashRegister = new CashRegister();
        this.cashRegisterCalculator = new CashRegisterCalculator(cashRegister);
        this. transactionManager = new TransactionManager(cashRegister);
        this.tender = new ArrayList<>();
    }

    @Test
    public void whenFundsGivenAreGreaterThanMerchandiseValueChangeOwingIsCalculated() throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
        this.cashRegisterCalculator.getCashRegisterDenominations();

        ArrayList<Double> expectedChange = new ArrayList<>();
        expectedChange.add(20.00);
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(50.00);

        ArrayList<Double> actualChange = transactionManager.conductTransaction(tender, 30.00);

        assertEquals(expectedChange, actualChange);
    }

    @Test
    public void whenFundsGivenAreLessThanMerchandiseValueExceptionIsThrown() throws InsufficientCashRegisterFundsException{
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(99.00);
        String expectedMessage = "Unable to process transaction; insufficient customer payment.";
        String actualMessage = "";

        try {
            transactionManager.conductTransaction(tender, 100.00);
        }
        catch (InsufficientCustomerPaymentException message) {
            actualMessage = message.getMessage();
        }

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenFundsGivenAreEqualToMerchandiseValueCustomerWillNotReceiveAnyChange() throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
        ArrayList<Double> expectedChange = new ArrayList<>();
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(100.00);
        ArrayList<Double> actualChange = transactionManager.conductTransaction(tender, 100.00);

        assertEquals(expectedChange, actualChange);
    }

    @Test
    public void whenCashRegisterContainsSufficientFundsChangeCanBeProvided() throws InsufficientCashRegisterFundsException, InsufficientCustomerPaymentException{
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(50.00);
        ArrayList<Double> expectedChange = new ArrayList<>();
        expectedChange.add(20.00);
        expectedChange.add(20.00);

        ArrayList<Double> actualChange = transactionManager.conductTransaction(tender, 10.00);

        assertEquals(expectedChange, actualChange);

    }

    @Test
    public void whenCashRegisterDoesNotContainSufficientFundsToProvideChangeExceptionIsThrown()throws InsufficientCustomerPaymentException {
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(5000.00);

        String expectedMessage = "Unable to provide change; insufficient funds available";
        String actualMessage = "";

        try {
            transactionManager.conductTransaction(tender, 10.00);
        }
        catch (InsufficientCashRegisterFundsException message) {
            actualMessage = message.getMessage();
        }

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void whenRequiredChangeIsEqualToASingleDenominationThatIsSufficientChangeIsGivenInThatDenomination()throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException {
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(20.00);
        ArrayList<Double> expectedChangeDenomination = new ArrayList<>();
        expectedChangeDenomination.add(10.00);

        ArrayList<Double> actualChangeDenomination = (transactionManager.conductTransaction(tender, 10.00));

        assertEquals(expectedChangeDenomination,actualChangeDenomination);
    }

    @Test
    public void whenRequiredChangeIsEqualToADenominationThatIsUnavailableNextDenominationDownIsUsedToMakeChange() throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{

        cashRegisterCalculator.getCashRegisterDenominations().replace(10.00, 0);
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(20.00);

        ArrayList<Double> expectedChangeDenomination = new ArrayList<>();
        expectedChangeDenomination.add(5.00);
        expectedChangeDenomination.add(5.00);

        ArrayList<Double> actualChangeDenomination = transactionManager.conductTransaction(tender, 10.00);

        assertEquals(expectedChangeDenomination,actualChangeDenomination);
    }

    @Test
    public void whenRequiredChangeIsNotEqualToADenominationOneOrMoreDenominationAreUsedToMakeUpChangeRequired()throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(50.00);

        ArrayList<Double> expectedChangeDenomination = new ArrayList<>();
        expectedChangeDenomination.add(20.00);
        expectedChangeDenomination.add(10.00);

        ArrayList<Double> actualChangeDenomination = transactionManager.conductTransaction(tender, 20.00);

        assertEquals(expectedChangeDenomination,actualChangeDenomination);
    }

    @Test
    public void whenChangeCannotBeGivenWithASingleDenominationMultipleDenominationsAreUsed()throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{

        cashRegisterCalculator.getCashRegisterDenominations().replace(10.00, 0);
        cashRegisterCalculator.getCashRegisterDenominations().replace(5.00, 1);

        ArrayList<Double> tender = new ArrayList<>();
        tender.add(20.00);

        ArrayList<Double> expectedChangeDenomination = new ArrayList<>();
        expectedChangeDenomination.add(5.00);
        expectedChangeDenomination.add(2.00);
        expectedChangeDenomination.add(2.00);
        expectedChangeDenomination.add(1.00);

        ArrayList<Double> actualChangeDenomination = transactionManager.conductTransaction(tender, 10.00);

        assertEquals(expectedChangeDenomination,actualChangeDenomination);
    }

    @Test
    public void whenChangeRequiredIsNotAWholeNumberChangeIsMadeUpUsingMultipleDenominations()throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(10.00);

        ArrayList<Double> expectedChangeDenomination = new ArrayList<>();
        expectedChangeDenomination.add(2.00);
        expectedChangeDenomination.add(2.00);
        expectedChangeDenomination.add(0.5);

        ArrayList<Double> actualChangeDenomination = transactionManager.conductTransaction(tender,5.50);

        assertEquals(expectedChangeDenomination,actualChangeDenomination);
    }

    @Test
    public void whenChangeRequiredCannotBeMadeFromAvailableCashRegisterDenominationsThrowsException() throws InsufficientCustomerPaymentException {

        cashRegisterCalculator.getCashRegisterDenominations().replace(10.00, 0);
        cashRegisterCalculator.getCashRegisterDenominations().replace(5.00, 0);
        cashRegisterCalculator.getCashRegisterDenominations().replace(2.00, 0);
        cashRegisterCalculator.getCashRegisterDenominations().replace(1.00, 0);
        cashRegisterCalculator.getCashRegisterDenominations().replace(0.50, 0);
        cashRegisterCalculator.getCashRegisterDenominations().replace(0.20, 0);
        cashRegisterCalculator.getCashRegisterDenominations().replace(0.10, 0);
        cashRegisterCalculator.getCashRegisterDenominations().replace(0.05, 0);

        ArrayList<Double> tender = new ArrayList<>();
        tender.add(20.00);

        String expectedMessage = "Unable to provide change; insufficient funds available";

        String actualMessage = "";


        try {
            transactionManager.conductTransaction(tender, 10.00);
        }
        catch (InsufficientCashRegisterFundsException message){
            actualMessage = message.getMessage();
        }

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void whenChangeIsGivenInASingleDenominationItIsRemovedFromCashRegister()throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(20.00);
        transactionManager.conductTransaction(tender, 10.00);

        Integer expectedNumberOf10sInTill = 9;
        Integer actualNumberOf10sInTill = cashRegisterCalculator.getCashRegisterDenominations().get(10.00);

        assertEquals(expectedNumberOf10sInTill, actualNumberOf10sInTill);
    }

    @Test
    public void whenChangeIsGivenInMultipleDenominationsTheyAreRemovedFromCashRegister()throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
        ArrayList<Double> tender = new ArrayList<>();
        tender.add(50.00);
        transactionManager.conductTransaction(tender, 20.00);

        ArrayList<Integer> expectedNumberOfDenominationsInTill = new ArrayList<>();
        expectedNumberOfDenominationsInTill.add(4);
        expectedNumberOfDenominationsInTill.add(9);

        ArrayList<Integer> actualNumberOfDenominationsInTill = new ArrayList<>();
        actualNumberOfDenominationsInTill.add(cashRegisterCalculator.getCashRegisterDenominations().get(20.00));
        actualNumberOfDenominationsInTill.add(cashRegisterCalculator.getCashRegisterDenominations().get(10.00));

        assertEquals(expectedNumberOfDenominationsInTill, actualNumberOfDenominationsInTill);
    }

    @Test
    public void singleDenominationsGivenAsCustomerPaymentAreAddedToCashRegister()throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
        tender.add(10.00);
        transactionManager.conductTransaction(tender, 5.00);

        Integer expectedNumberOf10sInTill = 11;
        Integer actualNumberOf10sInTill = cashRegisterCalculator.getCashRegisterDenominations().get(10.00);

        assertEquals(expectedNumberOf10sInTill, actualNumberOf10sInTill);
    }
//
//    @Test
//    public void mulitipleDenominationsGivenAsCustomerPaymentAreAddedToCashRegister()throws InsufficientCustomerPaymentException, InsufficientCashRegisterFundsException{
//
//    }


}