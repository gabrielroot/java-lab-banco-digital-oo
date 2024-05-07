package account;


import bank.Bank;
import client.Client;
import exceptions.InsufficientFundsException;
import exceptions.SameDestinyTransactionException;
import interfaces.IAccount;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
