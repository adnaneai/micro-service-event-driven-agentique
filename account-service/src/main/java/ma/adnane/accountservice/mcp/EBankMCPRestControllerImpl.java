package ma.adnane.accountservice.mcp;

import lombok.AllArgsConstructor;
import ma.adnane.accountservice.entities.Account;
import ma.adnane.accountservice.entities.AccountTransaction;
import ma.adnane.accountservice.enums.AccountType;
import ma.adnane.accountservice.service.EBankService;
import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EBankMCPRestControllerImpl implements EBankMCPRestController{
    private EBankService eBankService;

    @Override
    @McpTool(description = "Get Account By Id")
    public Account getAccountById(@McpArg(description = "The account id") String accountId) {
        return eBankService.getAccountById(accountId);
    }

    @Override
    @McpTool(description = "Get All Accounts ")
    public List<Account> getAllAccounts() {
        return eBankService.getAllAccounts();
    }

    @Override
    @McpTool(description = "Save a new Account")
    public Account saveNewAccount(@McpArg(description = "The initial balance") double initialBalance,
                                  @McpArg(description = "The account type")AccountType accountType,
                                  @McpArg(description = "The account currency")String currency,
                                  @McpArg(description = "The customer id")Long customerID) {
        return eBankService.saveNewAccount(initialBalance,accountType,currency,customerID);
    }

    @Override
    @McpTool(description = "Credit Account")
    public AccountTransaction credit(@McpArg(description = "The account id")String accountId,
                                     @McpArg(description = "The credited amount")double amount) {
        return eBankService.credit(accountId,amount);
    }

    @Override
    @McpTool(description = "Debit Account")
    public AccountTransaction debit(@McpArg(description = "The account id")String accountId,
                                    @McpArg(description = "The debited amount")double amount) {
        return eBankService.debit(accountId,amount);
    }
}
