package GKG.Digital.Library.Management.System.service;

import GKG.Digital.Library.Management.System.dto.CreateBookRequest;
import GKG.Digital.Library.Management.System.entities.Author;
import GKG.Digital.Library.Management.System.entities.Book;
import GKG.Digital.Library.Management.System.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {


    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;


    public Book addBook(CreateBookRequest createBookRequest) {
            Book book = Book.builder()
                            .name(createBookRequest.getName())
                            .genre(createBookRequest.getGenre())
                            .build();

            Author author = Author.builder()
                                .name(createBookRequest.getAuthorName())
                                .email(createBookRequest.getAuthorEmail())
                                .country(createBookRequest.getAuthorCountry())
                                .build();

            Author authorFromDB = authorService.updateOrCreate(author);
            book.setAuthor(authorFromDB);
            book.setAvailable(true);
            return bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public int findNumberOfBooksIssued(Long student_id) {
        return bookRepository.findNumberOfBooksIssued(student_id);
    }
}
