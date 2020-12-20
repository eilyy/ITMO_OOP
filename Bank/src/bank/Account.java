package bank;

public abstract class Account {
    protected int id;
    protected int daysCounter = 1;
    protected double balance = 0;
    protected double debt = 0;

    public int getId() {
        return this.id;
    }

    public double getBalance() {
        return this.balance;
    }

    public void transfer(int recipientId, int amount) throws Exception {
        throw new Exception("Unable to transfer money from this account");
    }

    public void payOff(double amount) throws Exception {
        this.debt -= amount;
    }

    public void replenish(double amount) {
        this.balance += amount;
    }

    public abstract void nextDay();
}
