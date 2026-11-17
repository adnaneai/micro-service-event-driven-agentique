package ma.adnane.accountservice.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.adnane.accountservice.enums.TransactionType;

import java.time.LocalDateTime;
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateTime;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne
    private Account account;
}
