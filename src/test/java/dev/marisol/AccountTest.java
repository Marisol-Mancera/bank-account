package dev.marisol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void shouldThrowExceptionWhenWithdrawMoreThanBalance() {
        float balance = 50f;
        float anualTaxRate = 0.5f;
        float withdraw = 100f;
        float delta = 0.01f;

        Account account = new Account(balance, anualTaxRate);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(withdraw));

        assertEquals(balance, account.getBalance(), delta);
        assertEquals(0, account.getWithdrawCount());

    }

    @Test
    public void shouldApplyMonthlyInterestToBalance() {
        float balance = 100f;
        float anualTaxRate = 0.05f;
        float applyMonthlyInterest = balance + (balance * anualTaxRate / 12f);
        float delta = 0.01f;

        Account account = new Account(balance, anualTaxRate);
        account.applyMonthlyInterest();

        assertEquals(applyMonthlyInterest, account.getBalance(), delta);

    }

    @Test
    public void shouldApplyMonthlyStatementSubtractingCommissionThenInterest() {
        float balance = 100f;
        float anualTaxRate = 0.05f;
        float fee = 1.5f;
        float delta = 0.01f;

        Account account = new Account(balance, anualTaxRate);
        account.setMonthlyFee(fee);
        account.monthlyStatement();

        float afterfeeBalance = balance - fee;
        float expectedBalance = afterfeeBalance + (afterfeeBalance * anualTaxRate / 12f);

        assertEquals( expectedBalance, account.getBalance(), delta  );
    }
    
    @Test
    public void shouldPrintSummary(){
        float balance = 100f;
        float anualTaxRate = 0.05f;
        float fee = 1.5f;

        Account account = new Account(balance, anualTaxRate);
        account.depositCount = 5; // Simulando depósitos
        account.withdrawCount = 3; // Simulando retiros 
        account.setMonthlyFee(fee);

        String summary = account.printSummary();

        assertTrue (summary.contains("Balance:"));
        assertTrue (summary.contains("Depósitos: 5"));
        assertTrue (summary.contains("Retiros: 3"));
        assertTrue (summary.contains("Tasa Anual: 0.05"));
        assertTrue (summary.contains("Comisión Mensual: 1.5"));
    }
}
