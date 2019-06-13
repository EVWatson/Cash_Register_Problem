import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashRegisterTest {

    CashRegister cashRegister;

    @Before
    public void setUp(){
        this.cashRegister = new CashRegister();
    }

    @Test
    public void customerHasSufficientFundsToPayWhenAmountGivenIsGreaterOrEqualToMerchandiseValue(){
        int merchValue = 100;
        int custmerFunds = 110;

        boolean result = cashRegister.areCustomerFundsSufficient(custmerFunds, merchValue);

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


}