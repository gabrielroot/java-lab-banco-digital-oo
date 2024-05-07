import account.Account;
import account.CheckingAccount;
import account.SavingAccount;
import bank.Bank;
import client.Client;
import exceptions.InsufficientFundsException;
import exceptions.SameDestinyTransactionException;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank brazeco = new Bank("Brazeco");
        Client carla = new Client("Carla", 22);

        Account checkingAccount = new CheckingAccount(brazeco, carla);
        Account savingAccount = new SavingAccount(brazeco, carla);

        brazeco.getAccounts().add(checkingAccount);
        brazeco.getAccounts().add(savingAccount);

        Scanner scanner = new Scanner(System.in);
        int option = 1;

        while (option != 0) {
            showMenuBank(brazeco, checkingAccount);
            option = scanner.nextInt();

            switch (option) {
                case 1: manageAccount(checkingAccount); break;
                case 2: manageAccount(savingAccount); break;
                case 0: break;
                default: System.out.println("Opção inválida."); break;
            }
        }
        scanner.close();
    }

    private static void transfer(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o número da conta: ");
        int accountNumber = scanner.nextInt();

        Optional<Account> accountFound = account.getBank().getAccounts().stream()
            .filter(bankAccount -> bankAccount.getAccountNumber() == accountNumber)
            .findFirst();

        if (accountFound.isEmpty()) {
            System.out.println("A conta não foi encontrada.");
            return;
        }

        System.out.print("Informe o valor: ");
        double amount = scanner.nextDouble();
        try {
            account.transfer(amount, accountFound.get());
        } catch (InsufficientFundsException | SameDestinyTransactionException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void withdraw(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o valor: ");
        double amount = scanner.nextDouble();
        try {
            account.withdraw(amount);
        } catch (InsufficientFundsException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void deposit(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o valor: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private static void manageAccount(Account account) {
        Scanner scanner = new Scanner(System.in);

        int option = 1;

        while (option != 0) {
            showMenuAccount(account);
            option = scanner.nextInt();

            switch (option) {
                case 1: account.showExtract(); break;
                case 2: Main.deposit(account); break;
                case 3: Main.withdraw(account); break;
                case 4: Main.transfer(account); break;
                case 0: break;
                default: System.out.println("Opção inválida."); break;
            }
        }
    }

    private static void showMenuBank(Bank bank, Account account) {
        System.out.println("Bem-vindo(a) ao "
            .concat(bank.getName())
            .concat(", ")
            .concat(account.getClient().getName())
            .concat("!"));
        System.out.println("1 - Gerir conta corrente");
        System.out.println("2 - Gerir conta poupança");
        System.out.println("0 - Sair do app");
    }

    private static void showMenuAccount(Account account) {
        boolean isSavinAccount = account instanceof SavingAccount;

        System.out.println(isSavinAccount ? "- CONTA POUPANÇA -" : "- CONTA CORRENTE -");
        System.out.println("1 - Ver extrato");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println(
                "4 - Transferir"
                + ((isSavinAccount) ? "" : " (Uma taxa de R$ "
                + ((CheckingAccount) account).getTransactionTax()
                + " será cobrada.)")
        );
        System.out.println("0 - Encerrar sessão na conta");
    }
}
