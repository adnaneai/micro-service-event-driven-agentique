package ma.adnane.accountservice;

import ma.adnane.accountservice.enums.AccountType;
import ma.adnane.accountservice.service.EBankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(EBankService eBankService){
        return args -> {
          eBankService.saveNewAccount(10000,
                  AccountType.CURRENT_ACCOUNT,
                  "MAD",
                  1L);

            eBankService.saveNewAccount(9500,
                    AccountType.SAVING_ACCOUNT,
                    "MAD",
                    2L);

            eBankService.saveNewAccount(12000,
                    AccountType.CURRENT_ACCOUNT,
                    "MAD",
                    3L);
            eBankService.getAllAccounts().forEach(account -> {
                for (int i = 0; i < 5; i++) {
                    eBankService.credit(account.getId(),1+Math.random()*200000);
                    eBankService.debit(account.getId(), 1+Math.random()*20000);
                }
            });
        };
    }
}
