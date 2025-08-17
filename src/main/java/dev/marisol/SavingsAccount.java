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

}
