package bank;

import java.util.HashMap;
import java.util.Vector;

public class Client {
    private String name;
    private String surname;
    private String address;
    private String passportNumber;

    private HashMap<Integer, Vector<Integer>> ownAccounts = new HashMap<>();

    private boolean status = false;

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Client(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public Client(String name, String surname, String address, String passportNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportNumber = passportNumber;
        this.status = true;
    }

    public void setAddress(String address) {
        this.address = address;
        if(this.passportNumber != null)
            this.status = true;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        if(this.address != null)
            this.status = true;
    }

    public int getAccount(int bankId, int accountType) throws Exception {
        for(int i : ownAccounts.get(bankId)) {
            if(String.valueOf(i).startsWith(String.valueOf(accountType)))
                return i;
        }
        throw new Exception("Given account does not exist");
    }

    public boolean getStatus() {
        return this.status;
    }

    public void closeAccount(int bankId, int accountId) {
        for(int i = 0; i < this.ownAccounts.get(bankId).size(); i++) {
            if(this.ownAccounts.get(bankId).get(i) == accountId)
                this.ownAccounts.get(bankId).remove(i);
            if(this.ownAccounts.get(bankId).isEmpty())
                this.ownAccounts.remove(bankId);
        }
    }

    public boolean isOldClient(int bankId) {
        return this.ownAccounts.containsKey(bankId);
    }

    public void newBank(int bankId) {
        this.ownAccounts.put(bankId, new Vector<>());
    }

    public void newAccount(int bankId, int accountId) {
        this.ownAccounts.get(bankId).add(accountId);
    }
}
