package bank.accounts;

import bank.*;

public class DebitAccount extends Account {
    private double interest;
    private double interestSum = 0;
    private double commission = 0;

    private Client accountHolder;
    private Bank provider;

    public DebitAccount(int id, Client client, Bank bank, double interest, double commission) {
        this.id = id;
        this.accountHolder = client;
        this.provider = bank;
        this.interest = interest;
        if(!this.accountHolder.getStatus())
            this.commission = commission;
    }

    public void nextDay() {
        this.daysCounter++;
        if(this.accountHolder.getStatus())
            this.commission = 0;
        this.interestSum += this.balance * this.interest / 365;
        if(this.daysCounter == 31) {
            this.daysCounter = 1;
            this.replenish(this.interestSum);
            this.interestSum = 0;
        }
    }

    @Override
    public void payOff(double amount) throws Exception {
        throw new Exception("You can not pay off a debt in debit account");
    }

    @Override
    public void transfer(int recipientId, int amount) throws Exception {
        if(this.balance < amount * (this.commission + 1))
            throw new Exception("There is not enough money on this account");
        if(String.valueOf(recipientId).startsWith("3"))
            throw new Exception("Unable to transfer money to deposit account");

        double amountToSend = amount * (this.commission + 1);
        Account recipientAccount = Database.getInstance().getBankAccount(recipientId);

        if(recipientAccount == null)
            throw new Exception("Given recipient account does not exist");

        this.balance -= amountToSend;

        if(String.valueOf(recipientId).startsWith("1"))
            recipientAccount.replenish(amount);
        else
            recipientAccount.payOff(amount);

        this.provider.newTransaction(new Transaction(this.id, amountToSend, recipientId, amount));
    }

    public void withdraw(double amount) {
        if (amount > this.balance)
            throw new IllegalArgumentException("Overdraft is unavailable for debit accounts");
        this.balance -= amount * (this.commission + 1);
    }
}
