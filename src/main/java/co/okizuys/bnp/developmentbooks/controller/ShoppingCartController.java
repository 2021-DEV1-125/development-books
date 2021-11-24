package co.okizuys.bnp.developmentbooks.controller;

import co.okizuys.bnp.developmentbooks.domain.ShoppingCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @GetMapping("/price/calculate")
    public BigDecimal getPrice(@RequestBody ShoppingCart shoppingCart) {
        return BigDecimal.ZERO;
    }
}
