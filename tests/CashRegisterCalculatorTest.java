import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class CashRegisterCalculatorTest {

    private CashRegisterCalculator cashRegisterCalculator;

    @Before
    public void setUp(){
        CashRegister cashRegister = new CashRegister();
        this.cashRegisterCalculator = new CashRegisterCalculator(cashRegister);
    }



    @Test
    public void calculateTotalCashRegisterFundsReturnsCurrentCashRegisterValue(){

        int expectedResult = 334;

        int actualResult = cashRegisterCalculator.getCashRegisterBalance();

        assertEquals(expectedResult, actualResult);
    }



}