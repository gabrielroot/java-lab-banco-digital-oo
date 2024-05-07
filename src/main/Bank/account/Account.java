package account;

import client.Client;
import interfaces.IAccount;

public abstract class Account implements IAccount {
    private static final int DEFAULT_AGENCY = 10;
    private static int SEQUENCY = 1;

    private int agency;
    private int accountNumber;
    private double balance;
    private Client client;

    public Account(Client client) {
        this.agency = DEFAULT_AGENCY;
        this.accountNumber = SEQUENCY++;
        this.balance = 0;
        this.client = client;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    @Override
    public void withdraw(double value) throws InsufficientFundsException {
        if (this.balance < value) {
            throw new InsufficientFundsException();
        }

    }

    @Override
    public void deposit(double value) {

    }

    @Override
    public void transfer(double value, IAccount accountDestiny) throws InsufficientFundsException, SameDestinyTransactionException {
        if (this.balance < value) {
            throw new InsufficientFundsException();
        }

        if (this.equals(accountDestiny)) {
            throw new SameDestinyTransactionException();
        }

        this.balance -= value;
        accountDestiny.deposit(value);
        System.out.printf(
            "Transferência de R$ %.2f, restando R$ %.2f em conta.\n",
            value,
            this.balance
        );
    }
}
