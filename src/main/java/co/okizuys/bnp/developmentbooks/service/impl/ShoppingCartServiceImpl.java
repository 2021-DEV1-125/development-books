package co.okizuys.bnp.developmentbooks.service.impl;

import co.okizuys.bnp.developmentbooks.domain.CartItem;
import co.okizuys.bnp.developmentbooks.domain.DiscountRule;
import co.okizuys.bnp.developmentbooks.domain.ShoppingCart;
import co.okizuys.bnp.developmentbooks.service.DiscountService;
import co.okizuys.bnp.developmentbooks.service.ShoppingCartService;
import co.okizuys.bnp.developmentbooks.service.validator.ShoppingCartValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final ShoppingCartValidator shoppingCartValidator;
  private final DiscountService discountService;

  public ShoppingCartServiceImpl(
      ShoppingCartValidator shoppingCartValidator, DiscountService discountService) {
    this.shoppingCartValidator = shoppingCartValidator;
    this.discountService = discountService;
  }

  @Override
  public BigDecimal calculatePrice(ShoppingCart cart) {
    shoppingCartValidator.validateShoppingCart(cart);

    if (cart.isEmpty()) {
      return BigDecimal.ZERO;
    }
    Set<CartItem> cartItems = cart.getItems();

    BigDecimal t1 = discountService.applyForDiscountRule(cartItems, DiscountRule.ALL_UNIQUE);
    BigDecimal t2 = discountService.applyForDiscountRule(cartItems, DiscountRule.TWO_UNIQUE);
    BigDecimal t3 = discountService.applyForDiscountRule(cartItems, DiscountRule.THREE_UNIQUE);
    BigDecimal t4 = discountService.applyForDiscountRule(cartItems, DiscountRule.FOUR_UNIQUE);
    BigDecimal t5 = discountService.applyForDiscountRule(cartItems, DiscountRule.FIVE_UNIQUE);

    return t1.min(t2).min(t3).min(t4).min(t5);
  }
}
