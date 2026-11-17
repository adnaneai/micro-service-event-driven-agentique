package ma.adnane.accountservice.service;

import lombok.AllArgsConstructor;
import ma.adnane.accountservice.entities.Account;
import ma.adnane.accountservice.entities.AccountTransaction;
import ma.adnane.accountservice.enums.AccountStatus;
import ma.adnane.accountservice.enums.AccountType;
import ma.adnane.accountservice.enums.TransactionType;
import ma.adnane.accountservice.exceptions.AccountNotFoundException;
import ma.adnane.accountservice.exceptions.BlanceNotSuffiecientException;
import ma.adnane.accountservice.exceptions.NegativeAmountException;
import ma.adnane.accountservice.repository.AccountRepository;
import ma.adnane.accountservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class EBankServiceImpl implements EBankService{
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Override
    public Account getAccountById(String accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new AccountNotFoundException("Account not found."));
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account saveNewAccount(double initialBalance,
                                  AccountType accountType,
                                  String currency,
                                  Long customerID) {
        if (initialBalance < 0) throw
            new NegativeArraySizeException("The amount should be positive.");
        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .accountStatus(AccountStatus.CREATED)
                .balance(initialBalance)
                .accountType(accountType)
                .customerId(customerID)
                .currency(currency).build();
        Account savedAccount = accountRepository.save(account);
        AccountTransaction accountTransaction = AccountTransaction.builder()
                .amount(initialBalance)
                .dateTime(LocalDateTime.now())
                .transactionType(TransactionType.CREDIT)
                .account(savedAccount).build();
        transactionRepository.save(accountTransaction);
        return account;
    }

    @Override
    public AccountTransaction credit(String accountId, double amount) {
        if (amount < 0) throw
            new NegativeAmountException("The amount should be positive.");
        Account account = getAccountById(accountId);
        AccountTransaction accountTransaction = AccountTransaction.builder()
                .account(account)
                .dateTime(LocalDateTime.now())
                .transactionType(TransactionType.CREDIT)
                .account(account).build();
        account.setBalance(account.getBalance()+amount);
        Account savedAccount = accountRepository.save(account);
        AccountTransaction transaction = transactionRepository.save(accountTransaction);
        return transaction ;
    }

    @Override
    public AccountTransaction debit(String accountId, double amount) {
        if (amount <= 0) throw
                new NegativeAmountException("The amount should be positive.");
        Account account = getAccountById(accountId);
        if (account.getBalance() < amount) throw
            new BlanceNotSuffiecientException("Blance Not Suffiecient.");
        AccountTransaction accountTransaction = AccountTransaction.builder()
                .account(account)
                .dateTime(LocalDateTime.now())
                .transactionType(TransactionType.DEBIT)
                .account(account).build();
        account.setBalance(account.getBalance()-amount);
        Account savedAccount = accountRepository.save(account);
        AccountTransaction transaction = transactionRepository.save(accountTransaction);
        return transaction ;
    }
}
