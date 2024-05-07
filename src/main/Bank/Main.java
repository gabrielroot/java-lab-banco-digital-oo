import account.Account;
import account.CheckingAccount;
import account.SavingAccount;
import client.Client;

public class Main {
    public static void main(String[] args) {
        Client carla = new Client("Carla", 22);

        Account checkingAccount = new CheckingAccount(carla);
        Account savingAccount = new SavingAccount(carla);

        checkingAccount.deposit(100);
        checkingAccount.transfer(50, savingAccount);

        checkingAccount.deposit(200);

        checkingAccount.showExtract();
    }
}
