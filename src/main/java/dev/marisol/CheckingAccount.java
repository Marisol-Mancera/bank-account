package dev.marisol;

public class CheckingAccount extends Account {

    protected float overdraft = 0f;

    public CheckingAccount(float balance, float anualTaxRate) {
        super(balance, anualTaxRate);
    }

    public float getOverdraft() {
        return overdraft;
    }
    
    @Override
    public void withdraw(float amount) {
        if (amount <= balance) {
            super.withdraw(amount);
            return;
        }
        float deficit = amount - balance; 
        balance = 0f;
        overdraft += deficit; 
        withdrawCount++; 
    }
    

    @Override
    public void deposit(float amount) {
        float remaining = amount;

        if (overdraft > 0f) {
            float pay = Math.min(remaining, overdraft);
            overdraft -= pay;
            remaining -= pay;
        }

        // 2) si sobra, recién entonces va al saldo
        if (remaining > 0f) {
            balance += remaining;
        }

        // 3) se cuenta exactamente una vez
        depositCount++;
    }

    @Override
    public void monthlyStatement() {
        super.monthlyStatement();
    }

    @Override
    public String printSummary() {
        String NL = System.lineSeparator();
        int tx = depositCount + withdrawCount;
        return "Saldo: " + balance + NL +
               "Comisión mensual: " + monthlyFee + NL +
               "Transacciones:" + tx + NL +
               "Sobregiro: " + overdraft;
    }
}
