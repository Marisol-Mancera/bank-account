package dev.marisol;

public class Account {

    protected float balance;
    protected float anualTaxRate;
    protected int depositCount;

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

    public void deposit( float depositCount) {
        this.balance += depositCount;
        this.depositCount++;
    }

    public int getDepositAmount() {
        return depositCount;
    }
    
}
