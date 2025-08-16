package dev.marisol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    @Test
    public void shouldInitializeAccountWithBalanceAndAnualRate() {

        float balance = 0f;
        float anualTaxRate = 0.5f;
        float delta = 0.01f;

        Account account = new Account(balance, anualTaxRate);

        assertEquals(balance, account.getBalance(), delta);
        assertEquals(anualTaxRate, account.getAnualTaxRate(), delta);
    }

}
