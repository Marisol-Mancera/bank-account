package dev.marisol;

public class Account {

    protected float balance;
    protected float anualTaxRate;
    protected int depositCount;
    protected int withdrawCount;
    protected float MonthlyInterest;

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
        return depositCount;
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

    public void applyMonthlyInterest() {
        float monthlyTaxRate = (balance * anualTaxRate) / 12f;
        this.balance += monthlyTaxRate;
    }

    public void setMonthlyFee(float fee) {
        this.MonthlyInterest = fee;
    }

    public void monthlyStatement() {
        balance -= MonthlyInterest;
        applyMonthlyInterest();
    }

    public String printSummary() {
        String summary = "Balance:" + balance + "/n" +
                "Depósitos: 5" + depositCount + "/n" +
                "Retiros: 3" + withdrawCount + "/n" +
                "Tasa Anual: 0.05" + anualTaxRate + "/n" +
                "Comisión Mensual: 1.5" + MonthlyInterest;
        return summary;
    }

}
