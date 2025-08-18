package dev.marisol;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

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

    @Test
    void ShouldRejectDepositWhenInactive() {
        float balance = 9000f;
        float annualTaxRate = 0.05f;
        float deposit = 1000f;
        float delta = 0.01f;

        SavingsAccount account = new SavingsAccount(balance, annualTaxRate);

        assertThrows(IllegalStateException.class, () -> {
            account.deposit(1000f);
        });

        assertEquals(balance, account.getBalance(), delta);
        assertEquals(0, account.getDepositCount());
    }

    @Test
    void shouldAllowDepositWhenActive() {
        float balance = 10000f;
        float annualTaxRate = 0.05f;
        float deposit = 1000f;
        float delta = 0.01f;

        SavingsAccount account = new SavingsAccount(balance, annualTaxRate);

        account.deposit(deposit);

        assertEquals(balance + deposit, account.getBalance(), delta);
        assertEquals(1, account.getDepositCount());
        assertTrue(account.isActive());

    }

    @Test
    void shouldRejectWithdrawWhenInactive(){
        float balance = 9000f;
        float annualTaxRate = 0.05f;
        float withdraw = 1000f;
        float delta = 0.01f;

        SavingsAccount account = new SavingsAccount(balance, annualTaxRate);

        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(withdraw);
        });

        assertEquals(balance, account.getBalance(), delta);
        assertEquals(0, account.getWithdrawCount());
    }

    @Test
    void shouldChargeExtraFeeWhenWithdrawIsMade() {
        float balance = 15000f;
        float annualTaxRate = 0.05f;
        float fee = 0f;
        float delta = 0.01f;

        SavingsAccount account = new SavingsAccount(balance, annualTaxRate);
        assertTrue(account.isActive());
        account.setMonthlyFee(fee);

         for (int i = 0; i < 6; i++) account.withdraw(100f); 
         account.monthlyStatement();

        float afterWithdrawals = balance - 600f;
        float extraFee = (6 - 4) * 1000f; 
        float totalFee = fee + extraFee; 
        float afterFees = afterWithdrawals - totalFee; 
        float expected = afterFees + (afterFees * (annualTaxRate / 12f));

        assertEquals(expected, account.getBalance(), delta);
        assertEquals(6, account.getWithdrawCount());
        assertTrue(account.isActive()); 
    }
}


