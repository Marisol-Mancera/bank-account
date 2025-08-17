package dev.marisol;

public class Account {

    protected float balance;
    protected float anualTaxRate;
    protected int depositCount;
    protected int withdrawCount;
    protected float monthlyFee = 0f;

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
        this.monthlyFee = fee;
    }

    public void monthlyStatement() {
        balance -= monthlyFee;
        applyMonthlyInterest();
    }

    public String printSummary() {
        String NL = System.lineSeparator();
        String summary = "Balance:" + balance + NL +
                "Depósitos: 5" + depositCount + NL +
                "Retiros: 3" + withdrawCount + NL +
                "Tasa Anual: 0.05" + anualTaxRate + NL +
                "Comisión Mensual: 1.5" + monthlyFee;
        return summary;
    }

}
