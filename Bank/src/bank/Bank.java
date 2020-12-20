package bank;

import bank.accounts.CreditAccount;
import bank.accounts.DebitAccount;
import bank.accounts.DepositAccount;
import idgenerator.IDGenerator;

import java.util.HashMap;
import java.util.Vector;

public class Bank {
    private int id;

    private Vector<Client> clients = new Vector<>();
    private Vector<Transaction> transactions = new Vector<>();

    private HashMap<Integer, Account> accounts = new HashMap<>();

    private double debitInterest, unverifiedCommission, creditLimit, debtInterest,
            overdraftCommission, depositInterest1, depositInterest2, depositInterest3;

    public Bank(double debitInterest, double unverifiedCommission, double creditLimit, double debtInterest, double overdraftCommission, double depositInterest1, double depositInterest2, double depositInterest3) {
        this.id = IDGenerator.generateBankId();
        new Database().addBank(this);
        this.debitInterest = debitInterest;
        this.unverifiedCommission = unverifiedCommission;
        this.creditLimit = creditLimit;
        this.debtInterest = debtInterest;
        this.overdraftCommission = overdraftCommission;
        this.depositInterest1 = depositInterest1;
        this.depositInterest2 = depositInterest2;
        this.depositInterest3 = depositInterest3;
    }

    public void newDebit(Client client) {
        int id = IDGenerator.generateDebitId();
        accounts.put(id, new DebitAccount(id, client, this, this.debitInterest, this.unverifiedCommission));
        if(!client.isOldClient(this.id))
            client.newBank(this.id);
        client.newAccount(this.id, id);
        if(!clients.contains(client))
            clients.add(client);
    }

    public void newCredit(Client client) {
        int id = IDGenerator.generateCreditId();
        accounts.put(id, new CreditAccount(id, client, this, this.creditLimit, this.debtInterest, this.overdraftCommission, this.unverifiedCommission));
        if(!client.isOldClient(this.id))
            client.newBank(this.id);
        client.newAccount(this.id, id);
        if(!clients.contains(client))
            clients.add(client);
    }

    public void newDeposit(Client client, double amount, int term) throws Exception {
        int id = IDGenerator.generateDepositId();
        if(amount < 1)
            throw new Exception("Deposit cannot be less than 1 rouble");
        if(term < 1)
            throw new Exception("Deposit term cannot be less than 1 month");
        double depositInterest;
        if(amount < 50000)
            depositInterest = this.depositInterest1;
        else if(amount < 100000)
            depositInterest = this.depositInterest2;
        else
            depositInterest = this.depositInterest3;
        accounts.put(id, new DepositAccount(client, this, id, amount, term, depositInterest));
        if(!client.isOldClient(this.id))
            client.newBank(this.id);
        client.newAccount(this.id, id);
        if(!clients.contains(client))
            clients.add(client);
    }

    public Account getAccount(int accountId) {
        return this.accounts.get(accountId);
    }

    public boolean providingAccount(int accountId) {
        return this.accounts.containsKey(accountId);
    }

    public void cancelTransaction(int transactionId) throws Exception {
        for(Transaction i : transactions) {
            if(i.getTransactionId() == transactionId) {
                i.cancel();
                transactions.remove(transactionId);
                return;
            }
        }

        throw new Exception("Given Transaction ID does not exist");
    }

    public void newTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public int getId() {
        return this.id;
    }

    public void skipDays(int days) {
        for(Account i : this.accounts.values()) {
            for(int j = 0; j < days; j++) {
                i.nextDay();
            }
        }
    }
}
