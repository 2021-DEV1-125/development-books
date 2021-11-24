package co.okizuys.bnp.developmentbooks.controller;

import co.okizuys.bnp.developmentbooks.domain.Book;
import co.okizuys.bnp.developmentbooks.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getAll() {
    return bookService.getAll();
  }
}
