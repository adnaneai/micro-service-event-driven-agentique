package ma.adnane.accountservice.repository;

import ma.adnane.accountservice.entities.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<AccountTransaction,Long> {
    Page<AccountTransaction> findByAccountId(String accountId, Pageable pageable);
}
