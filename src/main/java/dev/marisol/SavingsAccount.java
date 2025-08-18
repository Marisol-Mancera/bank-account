package dev.marisol;

public class SavingsAccount extends Account {
    private static final float MINIMUM_BALANCE = 10000f;
    private boolean isActive;

    private void updateActive() {
        this.isActive = (balance >= MINIMUM_BALANCE);
    }

    public SavingsAccount(float balance, float anualTaxRate) {

        super(balance, anualTaxRate);
        this.updateActive();
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void deposit(float deposit) {
        if (!isActive) {
            throw new IllegalStateException("Activa la cuenta para continuar");
        }
        super.deposit(deposit);
        this.updateActive();
    }

    @Override
    public void withdraw(float amount) {
        if (!isActive) {
            throw new IllegalStateException("Activa la cuenta para continuar");
        }
        super.withdraw(amount);
        this.updateActive();
    }

    @Override
    public void monthlyStatement() {
        int afterWithdrawals = Math.max(0, withdrawCount - 4);
        float extraFee = afterWithdrawals * 1000f;
        balance -= extraFee;
        super.monthlyStatement();
        this.updateActive();
    }
}
