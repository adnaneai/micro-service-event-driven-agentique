package ma.adnane.accountservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import ma.adnane.accountservice.enums.AccountStatus;
import ma.adnane.accountservice.enums.AccountType;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Account {
    @Id
    private String id;
    private LocalDateTime createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private String currency;
    private Long customerId;
}
