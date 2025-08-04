package GKG.Digital.Library.Management.System.repository;

import GKG.Digital.Library.Management.System.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select * from transaction where student_id = ?2 and book_id = ?1 and transaction_type = 0 and transaction_status = 1 order by updated_at desc limit 1;", nativeQuery = true)
    Transaction findLatestIssueTransaction(Long book_id, Long student_id);
}