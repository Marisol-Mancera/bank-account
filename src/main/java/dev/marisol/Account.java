package dev.marisol;

public class Account {

    protected float balance;
    protected float anualTaxRate;

    public Account(float balance, float anualTaxRate) {
        this.balance = balance;
        this.anualTaxRate = anualTaxRate;
    }

    public  float getBalance() {
        return balance;
    }

    public float getAnualTaxRate() {
        return anualTaxRate;
    }

}
