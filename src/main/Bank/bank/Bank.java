package bank;

import account.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Bank {
    private String name;
    private List<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

}
