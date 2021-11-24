package co.okizuys.bnp.developmentbooks.service.validator;

import co.okizuys.bnp.developmentbooks.domain.CartItem;
import co.okizuys.bnp.developmentbooks.domain.ShoppingCart;
import co.okizuys.bnp.developmentbooks.exception.ApiSubError;
import co.okizuys.bnp.developmentbooks.exception.ShoppingCartCustomException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class ShoppingCartValidator {

  private final BookValidator bookValidator;

  public ShoppingCartValidator(BookValidator bookValidator) {
    this.bookValidator = bookValidator;
  }

  public void validateShoppingCart(ShoppingCart shoppingCart) {

    List<Long> missingBookIds =
        shoppingCart.getItems().stream()
            .map(CartItem::getBookId)
            .filter(bookId -> !bookValidator.validateExistsById(bookId))
            .toList();

    if (!missingBookIds.isEmpty()) {
      List<ApiSubError> subErrors = constructApiSubErrors(missingBookIds);

      throw new ShoppingCartCustomException(
          "The requested books are not available in our bookstore", subErrors);
    }
  }

  private List<ApiSubError> constructApiSubErrors(List<Long> missingBookIds) {
    return IntStream.range(0, missingBookIds.size())
        .mapToObj(
            it ->
                new ApiSubError(
                    String.format("shoppingCart.items.[%s].bookId", it),
                    missingBookIds.get(it),
                    "Invalid book id"))
        .toList();
  }
}
