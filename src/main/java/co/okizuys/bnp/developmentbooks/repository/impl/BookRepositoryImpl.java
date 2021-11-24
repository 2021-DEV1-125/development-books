package co.okizuys.bnp.developmentbooks.repository.impl;

import co.okizuys.bnp.developmentbooks.domain.Author;
import co.okizuys.bnp.developmentbooks.domain.Book;
import co.okizuys.bnp.developmentbooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

  private static final BigDecimal BOOK_PRICE = BigDecimal.valueOf(50);

  private static final List<Book> books = new ArrayList<>();

  @Override
  public List<Book> getAll() {
    if (books.isEmpty()) {
      books.add(
          new Book("Clean Code", List.of(Author.ROBERT_MARTIN.fullName()), 2008, BOOK_PRICE));
      books.add(
          new Book(
              "The Clean Coder", List.of(Author.ROBERT_MARTIN.fullName()), 2011, BOOK_PRICE));
      books.add(
          new Book(
              "Clean Architecture", List.of(Author.ROBERT_MARTIN.fullName()), 2017, BOOK_PRICE));
      books.add(
          new Book(
              "Test Driven Development by Example",
              List.of(Author.KENT_BECK.fullName()),
              2003,
              BigDecimal.valueOf(50)));
      books.add(
          new Book(
              "Working Effectively With Legacy Code",
              List.of(Author.MICHAEL_C_FEATHERS.fullName()),
              2004,
              BigDecimal.valueOf(50)));
    }
    return books;
  }

  @Override
  public boolean existsById(long bookId) {
    return books.stream().anyMatch(book -> book.getBookId() == bookId);
  }
}
