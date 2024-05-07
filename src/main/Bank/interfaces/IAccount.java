package interfaces;

import exceptions.InsufficientFundsException;
import exceptions.SameDestinyTransactionException;

public interface IAccount {
    void withdraw(double value) throws InsufficientFundsException;

    void deposit(double value);

    void transfer(double value, IAccount accountDestiny) throws InsufficientFundsException, SameDestinyTransactionException;

    void showExtract();
}
