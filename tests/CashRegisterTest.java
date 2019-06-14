import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CashRegisterTest {

    private CashRegister cashRegister;

    @Before
    public void setUp(){
        this.cashRegister = new CashRegister();
    }

    @Test
    public void customerHasSufficientFundsToPayWhenAmountGivenIsGreaterOrEqualToMerchandiseValue(){
        int merchValue = 100;
        int customerFunds = 110;

        boolean result = cashRegister.areCustomerFundsSufficient(customerFunds, merchValue);

        assertTrue(result);
    }

    @Test
    public void whenFundsGivenAreLessThanMerchandiseValueCustomerIsUnableProceedWithTransaction(){
        int merchValue = 100;
        int customerFunds = 99;

        boolean result = cashRegister.areCustomerFundsSufficient(customerFunds, merchValue);

        assertFalse(result);
    }

    @Test
    public void whenFundsGivenAreEqualToMerchandiseValueCustomerWillNotReceiveAnyChange(){
        int merchValue = 100;
        int customerFunds = 100;

        boolean result = cashRegister.doesCustomerRequireChange(customerFunds, merchValue);

        assertFalse(result);
    }

    @Test
    public void whenFundsGivenAreGreaterThanMerchandiseValueTheCustomerWillReceiveChange(){
        int merchValue = 100;
        int customerFunds = 110;

        boolean result = cashRegister.doesCustomerRequireChange(customerFunds, merchValue);

        assertTrue(result);

    }

    @Test
    public void changeRequiredIsTheDifferenceBetweenTheCustomerFundsAndTheMerchandiseValue(){
        int merchValue = 100;
        int customerFunds = 110;

        int expectedValue = 10;
        int actualValue = cashRegister.calculateChange(customerFunds, merchValue);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void whenCashRegisterContainsSufficientFundsChangeCanBeProvided(){
        int changeRequired = 10;
        int cashRegisterFunds = 200;

        boolean result = cashRegister.areCashRegisterFundsSufficient(cashRegisterFunds, changeRequired);

        assertTrue(result);

    }

    @Test
    public void whenCashRegisterDoesNotContainSufficientFundsChangeCannotBeProvided(){
        int changeRequired = 10;
        int cashRegisterFunds = 9;

        boolean result = cashRegister.areCashRegisterFundsSufficient(cashRegisterFunds, changeRequired);

        assertFalse(result);

    }

    @Test
    public void calculateTotalCashRegisterFundsReturnsCurrentCashRegisterValue(){
        HashMap<String, Integer> cashRegisterContents = cashRegister.setUpCashRegister();

        int expectedResult = 800;

        int actualResult = cashRegister.calculateTotalCashRegisterFunds(cashRegisterContents);

        assertEquals(expectedResult, actualResult);
    }




}