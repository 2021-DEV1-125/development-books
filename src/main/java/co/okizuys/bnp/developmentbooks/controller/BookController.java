package co.okizuys.bnp.developmentbooks.controller;

import co.okizuys.bnp.developmentbooks.domain.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

  @GetMapping
  public List<Book> getAll() {
    return List.of();
  }
}
