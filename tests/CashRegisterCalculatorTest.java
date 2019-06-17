import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CashRegisterCalculatorTest {

    private CashRegisterCalculator cashRegisterCalculator;
    private Merchandise merchandise;
    private CashRegister cashRegister;

    @Before
    public void setUp(){
        this.cashRegister = new CashRegister();
        this.cashRegisterCalculator = new CashRegisterCalculator(cashRegister);
        this.merchandise = new Merchandise();
    }

    @Test
    public void whenFundsGivenAreGreaterThanMerchandiseValueTheCorrectChangeAmountIsReturned() throws InsufficientCashRegisterFundsException{
        int product = merchandise.getMerchandise().get("Whole gateau");
        int customerPayment = 50;

        int expectedChange = 20;

        int actualChange = cashRegisterCalculator.calculateChange(customerPayment, product);

        assertEquals(expectedChange, actualChange);
    }

//    @Test
//    public void whenChangeRequiredIsGreaterThanAvailableCashRegisterFundsThrowsException(){
//        int product = merchandise.getMerchandise().get("Whole gateau");
//        int customerPayment = 400;
//
//        boolean exceptionThrown = false;
//        try {
//            cashRegisterCalculator.calculateChange(customerPayment, product);
//        } catch (InsufficientCashRegisterFundsException e){
//
//        }
//
//
//    }

    @Test
    public void whenChangeRequiredCannotBeMadeFromAvailableCashRegisterDenominationsThrowsException(){

    }

    @Test
    public void customerHasSufficientFundsToPayWhenAmountGivenIsGreaterOrEqualToMerchandiseValue(){
        int merchValue = 100;
        int customerFunds = 110;

        boolean result = cashRegisterCalculator.areCustomerFundsSufficient(customerFunds, merchValue);

        assertTrue(result);
    }

    @Test
    public void whenFundsGivenAreLessThanMerchandiseValueCustomerIsUnableProceedWithTransaction(){
        int merchValue = 100;
        int customerFunds = 99;

        boolean result = cashRegisterCalculator.areCustomerFundsSufficient(customerFunds, merchValue);

        assertFalse(result);
    }

    @Test
    public void whenFundsGivenAreEqualToMerchandiseValueCustomerWillNotReceiveAnyChange() throws InsufficientCashRegisterFundsException{
        int merchValue = 100;
        int customerFunds = 100;

        int expectedChange = 0;

        int actualChange = cashRegisterCalculator.calculateChange(customerFunds, merchValue);

        assertEquals(expectedChange, actualChange);
    }

//    @Test
//    public void whenFundsGivenAreGreaterThanMerchandiseValueTheCustomerWillReceiveChange(){
//        int merchValue = 100;
//        int customerFunds = 110;
//
//        boolean result = cashRegisterCalculator.doesCustomerRequireChange(customerFunds, merchValue);
//
//        assertTrue(result);
//
//    }

    @Test
    public void changeRequiredIsTheDifferenceBetweenTheCustomerFundsAndTheMerchandiseValue() throws InsufficientCashRegisterFundsException{
        int merchValue = 100;
        int customerFunds = 110;

        int expectedValue = 10;
        int actualValue = cashRegisterCalculator.calculateChange(customerFunds, merchValue);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void whenCashRegisterContainsSufficientFundsChangeCanBeProvided() throws InsufficientCashRegisterFundsException{
        int customerPayment = 50;
        int merchandisePrice = merchandise.getMerchandise().get("Chocolate eclair");

        int expectedChange = 40;


        int actualChange = cashRegisterCalculator.calculateChange(customerPayment, merchandisePrice);

        assertEquals(expectedChange, actualChange);

    }

    @Test
    public void whenCashRegisterDoesNotContainSufficientFundsToProvideChangeExceptionIsThrown() {
        int customerPayment = 5000;
        int merchandisePrice = merchandise.getMerchandise().get("Chocolate eclair");

        String expectedMessage = "Unable to provide change; insufficient funds available";

        String actualMessage = "";
        try {
            cashRegisterCalculator.calculateChange(customerPayment, merchandisePrice);
        }
        catch (InsufficientCashRegisterFundsException message) {
           actualMessage = message.getMessage();
        }

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void calculateTotalCashRegisterFundsReturnsCurrentCashRegisterValue(){

        int expectedResult = 334;

        int actualResult = cashRegisterCalculator.calculateTotalCashRegisterFunds();

        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    public void givenSufficientCustomerPaymentAndSufficientCashRegisterFundsTransactionCanProceed(){
//        int merch = merchandise.getMerchandise().get("Cheesecake slice");
//        int customerPayment = 20;
//
//        TransactionStatus status = cashRegisterCalculator.determineTransactionType(merch, customerPayment);
//
//        String expectedResult = "Transaction can proceed";
//
//        String actualResult = cashRegisterCalculator.adviseTransactionType(status);
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    public void givenInsufficientCustomerPaymentAndSufficientCashRegisterFundsTransactionCannotProceed(){
//        int merch = merchandise.getMerchandise().get("Cheesecake slice");
//        int customerPayment = 10;
//
//        TransactionStatus status = cashRegisterCalculator.determineTransactionType(merch, customerPayment);
//
//        String expectedResult = "Insufficient funds available";
//
//        String actualResult = cashRegisterCalculator.adviseTransactionType(status);
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    public void givenSufficientCustomerPaymentAndInsufficientCashRegisterFundsTransactionCannotProceed(){
//        int merch = merchandise.getMerchandise().get("Cheesecake slice");
//        int customerPayment = 1000;
//
//        TransactionStatus status = cashRegisterCalculator.determineTransactionType(merch, customerPayment);
//
//        String expectedResult = "Insufficient funds available";
//
//        String actualResult = cashRegisterCalculator.adviseTransactionType(status);
//
//        assertEquals(expectedResult, actualResult);
//    }


}