package co.okizuys.bnp.developmentbooks.controller;

import co.okizuys.bnp.developmentbooks.domain.Book;
import co.okizuys.bnp.developmentbooks.domain.CartItem;
import co.okizuys.bnp.developmentbooks.domain.ShoppingCart;
import co.okizuys.bnp.developmentbooks.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ShoppingCartControllerTest extends AbstractControllerTest {

  private static final String SHOPPING_CART_PRICE_CALCULATION_API_PATH =
      "/shopping-cart/price/calculate";

  @Autowired public MockMvc mockMvc;
  @Autowired public ObjectMapper objectMapper;
  @Autowired public BookService bookService;

  @Test
  void getPrice_whenValidInputArguments_thenStatusOk() throws Exception {

    List<Book> books = bookService.getAll();
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 2));

    mockMvc
        .perform(
            post(SHOPPING_CART_PRICE_CALCULATION_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(shoppingCart)))
        .andExpect(status().isOk());
  }

  @Test
  void getPrice_whenInvalidBookId_thenStatusBadRequest() throws Exception {

    ShoppingCart shoppingCart = new ShoppingCart();

    shoppingCart.addItem(new CartItem(RandomUtils.nextLong(), 2));

    mockMvc
        .perform(
            post(SHOPPING_CART_PRICE_CALCULATION_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(shoppingCart)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getPrice_whenInvalidBooksCount_thenStatusBadRequest() throws Exception {

    ShoppingCart shoppingCart = new ShoppingCart();
    List<Book> books =
        List.of(new Book("Test book", List.of("Book author"), 2021, BigDecimal.valueOf(50)));
    shoppingCart.addItem(new CartItem(books.get(0).getBookId(), -2));

    mockMvc
        .perform(
            post(SHOPPING_CART_PRICE_CALCULATION_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(shoppingCart)))
        .andExpect(status().isBadRequest());
  }
}
