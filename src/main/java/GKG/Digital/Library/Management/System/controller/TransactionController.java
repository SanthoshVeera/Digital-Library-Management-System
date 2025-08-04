package GKG.Digital.Library.Management.System.controller;

import GKG.Digital.Library.Management.System.entities.Transaction;
import GKG.Digital.Library.Management.System.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping("/issue")
    Transaction addIssueTransaction(@RequestParam(value = "book-id") Long book_id,
                                    @RequestParam(value = "student-id") Long student_id)
                                            throws Exception{
        return transactionService.addIssueTransaction(book_id, student_id);
    }

    @PostMapping("/return")
    Transaction addReturnTransaction(@RequestParam(value = "book-id") Long book_id,
                                     @RequestParam(value = "student-id") Long student_id)
                                        throws Exception {
        return transactionService.addReturnTransaction(book_id, student_id);
    }

}
