package co.okizuys.bnp.developmentbooks.service;

import co.okizuys.bnp.developmentbooks.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getById(long bookId);
}
