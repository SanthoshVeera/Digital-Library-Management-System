package GKG.Digital.Library.Management.System.service;

import GKG.Digital.Library.Management.System.entities.*;
import GKG.Digital.Library.Management.System.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class TransactionService {

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    @Value("${Maximum.Number.Of.Books.Allowed}")
    int maxAllowedBooks;

    @Value("${fine.per.day}")
    int finePerDay;


    public Transaction addIssueTransaction(Long book_id, Long student_id) throws Exception{
        Student student = studentService.findByStudentId(student_id);

        if(student == null) {
            throw new Exception("Invalid Student ID");
        }

        Book book = bookService.findBookById(book_id);

        if(book == null) {
            throw new Exception("Invalid Book ID");
        }

        Transaction transaction = Transaction.builder()
                                                .transactionStatus(TransactionStatus.PENDING)
                                                .transactionType(TransactionType.ISSUE)
                                                .displayID(UUID.randomUUID().toString())
                                                .build();

        transaction = transactionRepository.save(transaction);

        try {

            int numberOfBooksIssued = bookService.findNumberOfBooksIssued(student_id);

            if(book.isAvailable() && numberOfBooksIssued < maxAllowedBooks) {


                transaction.setStudent(student);
                transaction.setBook(book);

                // Adding student_id to book & updating book status
                book.setStudent(student);
                book.setAvailable(false);
                bookService.addBook(book);


                transaction.setTransactionStatus(TransactionStatus.COMPLETED);
            } else {
                logger.info("Maximum books issued to the Student {}:{}", student.getName(), student.getId());
                transaction.setTransactionStatus(TransactionStatus.FAILED);
            }
        } catch (Exception exception) {
            logger.error(exception.toString());
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }
        finally {
            return transactionRepository.save(transaction);
        }
    }

    public Transaction addReturnTransaction(Long book_id, Long student_id) throws Exception {
        Student student = studentService.findByStudentId(student_id);

        if(student == null) {
            throw new Exception("Invalid Student ID");
        }

        Book book = bookService.findBookById(book_id);

        if(book == null) {
            throw new Exception("Invalid Book ID");
        }

        Transaction transaction = Transaction.builder()
                .transactionStatus(TransactionStatus.PENDING)
                .transactionType(TransactionType.RETURN)
                .displayID(UUID.randomUUID().toString())
                .build();

        transaction = transactionRepository.save(transaction);

        Date returnDate = transaction.getUpdatedAt();

        try {
            Transaction latestIssueTransaction = transactionRepository.findLatestIssueTransaction(book_id, student_id);

            if(latestIssueTransaction != null) {
                Date issueDate = latestIssueTransaction.getUpdatedAt();
                long fine = calculateFine(issueDate, returnDate);

                transaction.setFine(fine);
                transaction.setStudent(student);
                transaction.setBook(book);

                book.setAvailable(true);
                book.setStudent(null);

                transaction.setTransactionStatus(TransactionStatus.COMPLETED);
            } else {
                logger.info("Book:{} is not been issued to that student:{}",book_id,student_id);
                transaction.setTransactionStatus(TransactionStatus.FAILED);
            }
        } catch (Exception exception) {
            logger.error(exception.toString());
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }
        finally {
            return transactionRepository.save(transaction);
        }
    }

    private long calculateFine(Date issueDate, Date returnDate) {
        long diffInMillies = returnDate.getTime() - issueDate.getTime();
        long diffIndays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return (diffIndays > 14) ? (diffIndays - 14) * finePerDay : 0;
    }
}
