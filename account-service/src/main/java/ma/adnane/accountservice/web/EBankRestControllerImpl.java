package ma.adnane.accountservice.web;

import lombok.AllArgsConstructor;
import ma.adnane.accountservice.entities.Account;
import ma.adnane.accountservice.entities.AccountTransaction;
import ma.adnane.accountservice.enums.AccountType;
import ma.adnane.accountservice.service.EBankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ebank/accounts")
@AllArgsConstructor
public class EBankRestControllerImpl implements EBankRestController{
    private EBankService eBankService;
    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable String accountId) {
        return eBankService.getAccountById(accountId);
    }
    @GetMapping()
    public List<Account> getAllAccounts() {
        return eBankService.getAllAccounts();
    }
    @PostMapping("/save")
    public Account saveNewAccount(double initialBalance,
                                  AccountType accountType,
                                  String currency,
                                  Long customerID) {
        return eBankService.saveNewAccount(initialBalance, accountType, currency, customerID);
    }
    @PostMapping("/credit")
    public AccountTransaction credit(String accountId, double amount) {
        return eBankService.credit(accountId, amount);
    }
    @PostMapping("/debit")
    public AccountTransaction debit(String accountId, double amount) {
        return eBankService.debit(accountId, amount);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception exception){
        return ResponseEntity.internalServerError()
                .body(Map.of("errorMessage",exception.getMessage()));
    }
}
