package GKG.Digital.Library.Management.System.repository;

import GKG.Digital.Library.Management.System.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select count(*) from book where student_id = ?1", nativeQuery = true)
    int findNumberOfBooksIssued(Long student_id);
}