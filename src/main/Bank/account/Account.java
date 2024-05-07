package account;


import bank.Bank;
import client.Client;
import exceptions.InsufficientFundsException;
import exceptions.SameDestinyTransactionException;
import interfaces.IAccount;

public abstract class Account implements IAccount {
    protected static final int DEFAULT_AGENCY = 10;
    protected static int SEQUENCY = 1;

    protected int agency;
    protected int accountNumber;
    protected double balance;
    protected Client client;
    protected Bank bank;

    public Account(Bank bank, Client client) {
        this.agency = DEFAULT_AGENCY;
        this.accountNumber = SEQUENCY++;
        this.balance = 200;
        this.client = client;
        this.bank = bank;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void withdraw(double value) throws InsufficientFundsException {
        if (this.balance < value) {
            throw new InsufficientFundsException();
        }

        this.balance -= value;
        System.out.printf(
            "Dedução de R$ -%.2f, restando R$ %.2f em conta.\n",
            value,
            this.balance
        );
    }

    @Override
    public void deposit(double value) {
        this.balance += value;
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
