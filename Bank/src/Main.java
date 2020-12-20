import bank.Bank;
import bank.Client;
import bank.Database;

public class Main {

    public static void main(String[] args) {
        try {
            Database db = Database.getInstance();

            Bank sber = new Bank(0.03, 0.05, 200000, 0.05, 0.1, 0.03, 0.04, 0.05);
            Bank tinkoff = new Bank(0.04, 0.05, 300000, 0.04, 0.1, 0.05, 0.06, 0.07);

            Client ilya = new Client("Ilya", "Evdokimov", "1q23", "123456");
            Client seva = new Client("Seva", "Trutnev");

            sber.newDebit(ilya);
            sber.newCredit(ilya);
            sber.newDeposit(ilya, 500000, 12);
            sber.newDebit(seva);
            sber.newCredit(seva);
            sber.newDeposit(seva, 200000, 12);

            tinkoff.newDebit(ilya);
            tinkoff.newCredit(ilya);
            tinkoff.newDeposit(ilya, 500000, 12);
            tinkoff.newDebit(seva);
            tinkoff.newCredit(seva);
            tinkoff.newDeposit(seva, 200000, 12);

            db.getBankAccount(ilya.getAccount(1, 1)).replenish(20000);
            db.getBankAccount(ilya.getAccount(1, 1)).transfer(seva.getAccount(1, 1), 10000);
            db.skipDays(60);

            System.out.println(db.getBankAccount(ilya.getAccount(1, 1)).getBalance());
            System.out.println(db.getBankAccount(seva.getAccount(1, 1)).getBalance());
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}
