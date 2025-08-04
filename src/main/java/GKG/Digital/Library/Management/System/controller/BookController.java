package GKG.Digital.Library.Management.System.controller;

import GKG.Digital.Library.Management.System.dto.CreateBookRequest;
import GKG.Digital.Library.Management.System.entities.Book;
import GKG.Digital.Library.Management.System.service.BookService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
//    @PostMapping("/add")
    public Book addBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.addBook(createBookRequest);
    }


    @GetMapping("{id}")
    public Book findBookByID(@PathVariable Long id) {
        return bookService.findBookById(id);
    }
}
