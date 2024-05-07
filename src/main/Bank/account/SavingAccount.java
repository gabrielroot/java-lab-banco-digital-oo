package account;

import bank.Bank;
import client.Client;

public class SavingAccount extends Account {
    public SavingAccount(Bank bank, Client client) {
        super(bank, client);
    }

    @Override
    public void showExtract() {
        System.out.println("- EXTRATO SIMPLIFICADO [CONTA POUPANÇA]-");
        System.out.printf("    > Banco = %s\n", this.bank.getName());
        System.out.printf("    > Conta = %s\n", this.accountNumber);
        System.out.printf("    > Agência = %s\n", this.agency);
        System.out.printf("    > Saldo = %s\n", this.balance);
    }
}
