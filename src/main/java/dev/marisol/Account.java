package dev.marisol;

public class Account {

    protected float balance;
    protected float anualTaxRate;
    protected int depositCount;
    protected int withdrawCount;
    protected float applyMonthlyInterest;

    public Account(float balance, float anualTaxRate) {
        this.balance = balance;
        this.anualTaxRate = anualTaxRate;
    }

    public float getBalance() {
        return balance;
    }

    public float getAnualTaxRate() {
        return anualTaxRate;
    }

    public void deposit(float amount) {
        this.balance += amount;
        this.depositCount++;
    }

    public int getDepositCount() {
        return depositCount ;
    }

    public void withdraw(float amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("¡Ni en tus sueños, amig@!");
        }
        this.balance -= amount;
        this.withdrawCount++;
    }

    public int getWithdrawCount() {
        return withdrawCount;
    }

    public void applyMonthlyInterest(){
        float applyMonthlyInterest = (balance * anualTaxRate) / 12f;
        this.balance += applyMonthlyInterest;
    }

}
