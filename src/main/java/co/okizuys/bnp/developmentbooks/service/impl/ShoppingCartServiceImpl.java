package co.okizuys.bnp.developmentbooks.service.impl;

import co.okizuys.bnp.developmentbooks.domain.ShoppingCart;
import co.okizuys.bnp.developmentbooks.service.ShoppingCartService;
import co.okizuys.bnp.developmentbooks.service.validator.ShoppingCartValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final ShoppingCartValidator shoppingCartValidator;

  public ShoppingCartServiceImpl(ShoppingCartValidator shoppingCartValidator) {
    this.shoppingCartValidator = shoppingCartValidator;
  }

  @Override
  public BigDecimal calculatePrice(ShoppingCart cart) {
    shoppingCartValidator.validateShoppingCart(cart);

    return null;
  }
}
