package dev.marisol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

public class AccountTest {

    private static final float DELTA = 0.01f;
    private float balance = 100f;
    private float anualTaxRate = 0.05f;
    private Account account = new Account(balance, anualTaxRate);

    @BeforeEach
    public void setUp() {
        balance = 100f;
        anualTaxRate = 0.05f;
        account = new Account(balance, anualTaxRate);
    }

    @Test
    public void shouldInitializeAccountWithBalanceAndAnualRate() {


        assertEquals(balance, account.getBalance(), DELTA); // saldo
        assertEquals(anualTaxRate, account.getAnualTaxRate(), DELTA); // contador

    }

    @Test
    public void shouldIncrementBalanceWhenDepositIsMade() {
        float deposit = 50f;

        account.deposit(deposit);

        assertEquals(balance + deposit, account.getBalance(), DELTA);
        assertEquals(1, account.getDepositCount()); // contador

    }

    // Test para retiro exitoso
    @Test
    public void shoulSubstractBalanceWhenWithdrawIsMade() {
        float withdraw = 50f;
        float DELTA = 0.01f;

        account.withdraw(withdraw);

        assertEquals(balance - withdraw, account.getBalance(), DELTA);
        assertEquals(1, account.getWithdrawCount());
    }

    @Test
    public void shouldThrowExceptionWhenWithdrawMoreThanBalance() {
        float withdraw = 200f;

        Account account = new Account(balance, anualTaxRate);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(withdraw));

        assertEquals(balance, account.getBalance(), DELTA);
        assertEquals(0, account.getWithdrawCount());

    }

    @Test
    public void shouldApplyMonthlyInterestToBalance() {
        float applyMonthlyInterest = balance + (balance * anualTaxRate / 12f);

        Account account = new Account(balance, anualTaxRate);
        account.applyMonthlyInterest();

        assertEquals(applyMonthlyInterest, account.getBalance(), DELTA);

    }

    @Test
    public void shouldApplyMonthlyStatementSubtractingCommissionThenInterest() {
        float fee = 1.5f;

        Account account = new Account(balance, anualTaxRate);
        account.setMonthlyFee(fee);
        account.monthlyStatement();

        float afterfeeBalance = balance - fee;
        float expectedBalance = afterfeeBalance + (afterfeeBalance * anualTaxRate / 12f);

        assertEquals(expectedBalance, account.getBalance(), DELTA);
    }

    @Test
    public void shouldPrintSummary() {
        float fee = 1.5f;

        account.depositCount = 5; // Simulando depósitos
        account.withdrawCount = 3; // Simulando retiros
        account.setMonthlyFee(fee);

        String summary = account.printSummary();

        assertTrue(summary.contains("Balance:"));
        assertTrue(summary.contains("Depósitos: 5"));
        assertTrue(summary.contains("Retiros: 3"));
        assertTrue(summary.contains("Tasa Anual: 0.05"));
        assertTrue(summary.contains("Comisión Mensual: 1.5"));
    }
}
