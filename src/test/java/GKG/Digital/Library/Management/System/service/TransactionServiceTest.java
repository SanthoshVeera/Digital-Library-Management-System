package GKG.Digital.Library.Management.System.service;

import GKG.Digital.Library.Management.System.entities.*;
import GKG.Digital.Library.Management.System.repository.TransactionRepository;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    StudentService studentService;

    @Mock
    BookService bookService;

    @Test
    public void addIssueTransactionTest() throws Exception {

        transactionService.maxAllowedBooks = 3;

        Student mockStudent = Student.builder()
                                    .id(101l)
                                    .name("Santhosh")
                                    .email("abc@gmai.com")
                                    .rollNumber(101)
                                    .build();

        Transaction mockTransaction = Transaction.builder()
                .transactionStatus(TransactionStatus.PENDING)
                .transactionType(TransactionType.ISSUE)
                .displayID(UUID.randomUUID().toString())
                .build();

        Book mockBook = Book.builder()
                .id(105l)
                .isAvailable(true)
                .name("Test book")
                .build();

        Mockito.when(studentService.findByStudentId(Mockito.anyLong())).thenReturn(mockStudent);
        Mockito.when(bookService.findBookById(Mockito.eq(105l))).thenReturn(mockBook);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(mockTransaction);
        Mockito.when(bookService.findNumberOfBooksIssued(Mockito.eq(101l))).thenReturn(2);
        Mockito.when(bookService.addBook(Mockito.eq(mockBook))).thenReturn(mockBook);

        transactionService.addIssueTransaction(105l, 101l);

        Mockito.verify(studentService, Mockito.times(1)).findByStudentId(101l);
        Mockito.verify(bookService,Mockito.times(1)).findBookById(105l);
        Mockito.verify(transactionRepository, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(bookService, Mockito.times(1)).findNumberOfBooksIssued(101l);
        Mockito.verify(bookService, Mockito.times(1)).addBook(mockBook);

    }


    @Test(expected = Exception.class)
    public void addIssueTransactionTest2() throws Exception {
        Mockito.when(studentService.findByStudentId(Mockito.anyLong())).thenReturn(null);

        transactionService.addIssueTransaction(105l, 101l);
    }

}
