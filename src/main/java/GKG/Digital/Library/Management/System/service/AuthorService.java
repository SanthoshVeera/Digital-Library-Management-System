package GKG.Digital.Library.Management.System.service;

import GKG.Digital.Library.Management.System.entities.Author;
import GKG.Digital.Library.Management.System.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    AuthorRepository authorRepository;

    Author updateOrCreate(Author author) {
        Author authorFromDB = authorRepository.findByEmail(author.getEmail());

        if(authorFromDB == null) {
            logger.info("Author does not exist");
            authorFromDB = authorRepository.save(author);
        }
        return authorFromDB;
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> {
            new RuntimeException("Author not found");
            return null;
        });
    }
}
