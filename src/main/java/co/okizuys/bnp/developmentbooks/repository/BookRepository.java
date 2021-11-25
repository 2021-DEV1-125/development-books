package co.okizuys.bnp.developmentbooks.repository;

import co.okizuys.bnp.developmentbooks.domain.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    boolean existsById(long bookId);

    Book getById(long bookId);
}
