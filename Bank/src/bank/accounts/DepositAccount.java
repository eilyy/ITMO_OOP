package bank.accounts;

import bank.Account;
import bank.Client;
import bank.Bank;

public class DepositAccount extends Account {
    private double interest;
    private double interestSum = 0;
    private int term;
    private int monthsCounter = 0;

    Client accountHolder;
    Bank provider;

    public DepositAccount(Client accountHolder, Bank provider, int id, double amount, int term, double interest) {
        this.accountHolder = accountHolder;
        this.provider = provider;
        this.id = id;
        this.term = term;
        this.balance = amount;
        this.interest = interest;
    }

    public void nextDay() {
        this.daysCounter++;
        this.interestSum += this.balance * this.interest / 365;
        if(this.daysCounter == 31) {
            this.daysCounter = 1;
            this.monthsCounter++;
            this.balance += this.interestSum;
            this.interestSum = 0;
        }
        if(this.monthsCounter == this.term) {
            this.accountHolder.closeAccount(provider.getId(), this.getId());
        }
    }
}
