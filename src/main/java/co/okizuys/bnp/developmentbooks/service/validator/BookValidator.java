package co.okizuys.bnp.developmentbooks.service.validator;

import co.okizuys.bnp.developmentbooks.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {

    private final BookRepository bookRepository;

    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean validateExistsById(long bookId) {
        return bookRepository.existsById(bookId);
    }
}
