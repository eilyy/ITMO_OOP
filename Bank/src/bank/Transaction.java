package bank;

import idgenerator.IDGenerator;

public class Transaction {
    private int transactionId;
    private int senderId, recipientId;
    private double sentAmount, receivedAmount;

    public Transaction(int senderId, double sentAmount, int recipientId, double receivedAmount) {
        this.senderId = senderId;
        this.sentAmount = sentAmount;
        this.recipientId = recipientId;
        this.receivedAmount = receivedAmount;

        this.transactionId = IDGenerator.generateTransactionId();
    }

    public void cancel() {
        Account sender, recipient;
        sender = Database.getInstance().getBankAccount(senderId);
        recipient = Database.getInstance().getBankAccount(recipientId);

        if(String.valueOf(sender.id).startsWith("1"))
            sender.balance += this.sentAmount;
        else {
            sender.balance += this.sentAmount;
            sender.debt -= this.sentAmount;
        }

        if(String.valueOf(recipient.id).startsWith("1"))
            recipient.balance -= this.receivedAmount;
        else {
            recipient.debt += this.receivedAmount;
        }
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public int getSenderId() {
        return this.senderId;
    }

    public double getSentAmount() {
        return this.sentAmount;
    }

    public double getReceivedAmount() {
        return this.receivedAmount;
    }

    public int recipientId() {
        return this.recipientId;
    }
}
