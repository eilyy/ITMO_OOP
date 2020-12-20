package bank.accounts;

import bank.*;

public class CreditAccount extends Account {
    private double creditLimit;
    private double overdraftCommission;
    private double debtInterest;
    private double commission = 0;

    private Client accountHolder;
    private Bank provider;

    public CreditAccount(int id, Client client, Bank bank, double creditLimit, double debtInterest, double overdraftCommission, double commission) {
        this.id = id;
        this.accountHolder = client;
        this.provider = bank;
        this.creditLimit = creditLimit;
        this.balance = this.creditLimit;
        this.debtInterest = debtInterest;
        this.overdraftCommission = overdraftCommission;
        if(!this.accountHolder.getStatus())
            this.commission = commission;
    }

    public void nextDay() {
        this.daysCounter++;
        if(this.daysCounter == 31) {
            this.daysCounter = 1;
            this.balance = this.creditLimit;
            if(this.debt > 0) this.debt *= (1 + this.debtInterest);
        }
    }

    @Override
    public void transfer(int recipientId, int amount) throws Exception {
        if(String.valueOf(recipientId).startsWith("3"))
            throw new Exception("Unable to transfer money to deposit account");

        double amountToSend = (this.balance > 0) ? this.balance -= amount * (this.commission + 1) : amount * (this.commission + 1) * (this.overdraftCommission + 1);
        Account recipientAccount = new Database().getBankAccount(recipientId);

        if(recipientAccount == null)
        throw new Exception("Given recipient account does not exist");

        this.balance -= amountToSend;
        this.debt += amountToSend;

        if(String.valueOf(recipientId).startsWith("1"))
            recipientAccount.replenish(amount);
        else
            recipientAccount.payOff(amount);

        this.provider.newTransaction(new Transaction(this.id, amountToSend, recipientId, amount));
    }

    public void withdraw(double amount) {
        if(this.balance < 0)
            this.debt += amount * (1 + this.overdraftCommission) * (1 + this.commission);
        else
            this.debt += amount * (1 + this.commission);
        this.balance -= amount;
    }

    @Override
    public void replenish(double amount) {
        this.debt -= amount;
    }
}
