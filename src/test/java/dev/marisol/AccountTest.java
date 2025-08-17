package dev.marisol;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    @Test
    public void shouldInitializeAccountWithBalanceAndAnualRate() {

        float balance = 0f;
        float anualTaxRate = 0.5f;
        float delta = 0.01f;

        Account account = new Account(balance, anualTaxRate);

        assertEquals(balance, account.getBalance(), delta); // saldo
        assertEquals(anualTaxRate, account.getAnualTaxRate(), delta); // contador

    }

    @Test
    public void shouldIncrementBalanceWhenDepositIsMade() {
        float balance = 0f;
        float anualTaxRate = 0.5f;
        float deposit = 50f;
        float delta = 0.01f;

        Account account = new Account(balance, anualTaxRate);
        account.deposit(deposit);

        assertEquals(balance + deposit, account.getBalance(), delta);
        assertEquals(1, account.getDepositCount()); // contador

    }

    // Test para retiro exitoso
    @Test
    public void shoulSubstractBalanceWhenWithdrawIsMade() {
        float balance = 50f;
        float anualTaxRate = 0.5f;
        float withdraw = 50f;
        float delta = 0.01f;

        Account account = new Account(balance, anualTaxRate);
        account.withdraw(withdraw);

        assertEquals(balance - withdraw, account.getBalance(), delta);
        assertEquals(1, account.getWithdrawCount());
    }

}