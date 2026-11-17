package ma.adnane.accountservice.mcp;

import ma.adnane.accountservice.entities.Account;
import ma.adnane.accountservice.entities.AccountTransaction;
import ma.adnane.accountservice.enums.AccountType;

import java.util.List;

public interface EBankMCPRestController {
    Account getAccountById(String accountId);
    List<Account> getAllAccounts();
    Account saveNewAccount(double initialBalance,
                           AccountType accountType,
                           String currency,
                           Long customerID);
    AccountTransaction credit(String accountId, double amount);
    AccountTransaction debit(String accountId, double amount);
    }
