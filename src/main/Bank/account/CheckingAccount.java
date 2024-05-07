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
public class CheckingAccount extends Account{
    private double transactionTax;
    public CheckingAccount(Bank bank, Client client) {
        super(bank, client);
        this.transactionTax = 10.0;
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
        System.out.println("- EXTRATO SIMPLIFICADO [CONTA CORRENTE] -");
        System.out.printf("    > Banco = %s\n", this.bank.getName());
        System.out.printf("    > Conta = %s\n", this.accountNumber);
        System.out.printf("    > Agência = %s\n", this.agency);
        System.out.printf("    > Saldo = %s\n", this.balance);
    }
}
