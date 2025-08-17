package dev.marisol;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {

    @Test
    public void shouldBeInactiveWhenBalanceIsBelowThreshold() {
        float balance = 9000f;
        float annualTaxRate = 0.05f;

        SavingsAccount account = new SavingsAccount(balance, annualTaxRate);
        account.isActive();
        
        assertFalse(account.isActive());
        
    }

    @Test
    void shouldBeActiveWhenBalanceIsAtLeastThreshold() {
        float balance = 10000f;
        float annualTaxRate = 0.05f;

        SavingsAccount account = new SavingsAccount(balance, annualTaxRate);
        account.isActive();

        assertTrue(account.isActive());
    }

}
