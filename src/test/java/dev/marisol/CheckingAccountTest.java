package dev.marisol;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CheckingAccountTest {

    @Test
    void shouldAllowWithdrawBeyondBalance_andTrackOverdraft() {
        float balance = 1000;
        float annualTaxRate = 0.05f;
        float withdraw = 1200f;
        float delta = 0.01f;

        CheckingAccount account = new CheckingAccount(balance, annualTaxRate);

        account.withdraw(withdraw);

        assertEquals(0f, account.getBalance(), delta);
        assertEquals(200, account.getOverdraft(), delta);
        assertEquals(1, account.getWithdrawCount());
    }

    @Test
    void shouldReduceOverdraftFirst() {
    float balance = 1000f;
    float annualTaxRate = 0.05f;
    float withdraw = 1500f; 
    float deposit  = 300f;  
    float delta    = 0.0001f;

    CheckingAccount account = new CheckingAccount(balance, annualTaxRate);
    account.withdraw(withdraw);  
    account.deposit(deposit); 

    assertEquals(0f, account.getBalance(), delta);
    assertEquals(200f, account.getOverdraft(), delta);
    assertEquals(1, account.getDepositCount());
}

    @Test
    void shouldSendExcessToBalance() {
    float balance = 1000f;
    float annualTaxRate = 0.05f;
    float withdraw = 1500f; 
    float deposit  = 300f;  
    float delta    = 0.0001f;

    CheckingAccount account = new CheckingAccount(balance, annualTaxRate);
    account.withdraw(withdraw);
    account.deposit(deposit);
    
    assertEquals(200f, account.getOverdraft(), delta); 
    assertEquals(0f, account.getBalance(), delta);
}


 
    @Test
        void shouldReturnCheckingPrintableSummary() {
        float balance = 5000f;
        float annualTaxRate    = 0.05f;
        float fee = 12f;

        CheckingAccount account = new CheckingAccount(balance, annualTaxRate);
        account.setMonthlyFee(fee);
        account.deposit(100f);
        account.withdraw(50f);

        String summary = account.printSummary();

        assertTrue(summary.contains("Saldo:"));
        assertTrue(summary.contains("Comisión mensual:"));
        assertTrue(summary.contains("Transacciones:"));
        assertTrue(summary.contains("Sobregiro:"));
    }

     @Test
    void shouldNotChangeOverdraftOnMonthlyStatement() {
        float balance = 100f;
        float annualTaxRate = 0.12f;
        float withdraw= 150f;
        float deposit = 120f;
        float fee = 10f;
        float delta = 0.0001f;

        CheckingAccount account = new CheckingAccount(balance, annualTaxRate);        account.withdraw(withdraw);
        account.deposit(deposit);
        account.setMonthlyFee(fee);

        account.monthlyStatement();

        float afterFee = 70f - fee;
        float expected = afterFee + (afterFee * (annualTaxRate / 12f));

        assertEquals(0f, account.getOverdraft(), delta);
        assertEquals(expected, account.getBalance(), delta);
        assertEquals(1, account.getDepositCount());
        assertEquals(1, account.getWithdrawCount());
    }

}
