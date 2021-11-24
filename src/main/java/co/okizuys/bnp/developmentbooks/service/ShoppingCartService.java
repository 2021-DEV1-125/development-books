package co.okizuys.bnp.developmentbooks.service;

import co.okizuys.bnp.developmentbooks.domain.ShoppingCart;

import java.math.BigDecimal;

public interface ShoppingCartService {

    BigDecimal calculatePrice(ShoppingCart cart);
}
