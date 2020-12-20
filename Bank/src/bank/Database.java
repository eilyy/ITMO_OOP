package bank;

import java.util.HashMap;

public class Database {
    private static Database instance;
    private static HashMap<Integer, Bank> banks = new HashMap<>();

    public static Database getInstance() {
        if(instance == null)
            instance = new Database();
        return instance;
    }

    public void addBank(Bank bank) {
        banks.put(bank.getId(), bank);
    }

    public Bank getBank(int id) throws Exception {
        if(banks.get(id) == null)
            throw new Exception("Given bank does not exist");

        return banks.get(id);
    }

    public Account getBankAccount(int accountId) {
        for(Bank i : banks.values()) {
            if(i.providingAccount(accountId))
                return i.getAccount(accountId);
        }
        return null;
    }

    public void skipDays(int days) {
        for(Bank i : banks.values()) {
            i.skipDays(days);
        }
    }
}
