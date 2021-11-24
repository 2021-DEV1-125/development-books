package co.okizuys.bnp.developmentbooks.controller;

import co.okizuys.bnp.developmentbooks.domain.ShoppingCart;
import co.okizuys.bnp.developmentbooks.service.ShoppingCartService;
import co.okizuys.bnp.developmentbooks.service.impl.ShoppingCartServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  public ShoppingCartController(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  @PostMapping("/price/calculate")
  public BigDecimal getPrice(@Valid @RequestBody ShoppingCart shoppingCart) {
    return shoppingCartService.calculatePrice(shoppingCart);
  }
}
