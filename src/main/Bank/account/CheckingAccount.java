package account;

import client.Client;

public class CheckingAccount extends Account{
    private double transactionTax;
    public CheckingAccount(Bank bank, Client client) {
        super(bank, client);
        this.transactionTax = 10.0;
    }

    public double getTransactionTax() {
        return transactionTax;
    }

    public void setTransactionTax(double transactionTax) {
        this.transactionTax = transactionTax;
    }

    @Override
    public void withdraw(double value) throws InsufficientFundsException {
        if (this.balance < value + this.transactionTax) {
            throw new InsufficientFundsException();
        }

        this.balance -= value + this.transactionTax;
        System.out.printf(
            "Dedução de R$ -%.2f acrescida de uma taxa de R$ %.2f, restando %.2f em conta.\n",
            value,
            this.transactionTax,
            this.balance
        );
    }

    @Override
    public void transfer(double value, IAccount accountDestiny) throws InsufficientFundsException, SameDestinyTransactionException {
        if (this.balance < value + this.transactionTax) {
            throw new InsufficientFundsException();
        }

        if (this.equals(accountDestiny)) {
            throw new SameDestinyTransactionException();
        }

        this.balance -= value + this.transactionTax;
        accountDestiny.deposit(value);
        System.out.printf(
            "Transferência de R$ %.2f acrescida de uma taxa de R$ %.2f, restando R$ %.2f em conta.\n",
            value,
            this.transactionTax,
            this.balance
        );
    }

    @Override
    public void showExtract() {

    }
}
