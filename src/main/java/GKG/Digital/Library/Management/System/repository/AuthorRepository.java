package GKG.Digital.Library.Management.System.repository;

import GKG.Digital.Library.Management.System.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByEmail(String email);

}