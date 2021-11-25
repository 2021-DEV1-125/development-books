package co.okizuys.bnp.developmentbooks.service.impl;

import co.okizuys.bnp.developmentbooks.domain.Author;
import co.okizuys.bnp.developmentbooks.domain.Book;
import co.okizuys.bnp.developmentbooks.domain.CartItem;
import co.okizuys.bnp.developmentbooks.domain.ShoppingCart;
import co.okizuys.bnp.developmentbooks.service.DiscountService;
import co.okizuys.bnp.developmentbooks.service.ShoppingCartService;
import co.okizuys.bnp.developmentbooks.service.validator.ShoppingCartValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartServiceImplTest {

    private static final BigDecimal BOOK_PRICE = BigDecimal.valueOf(50);

    private final ShoppingCartValidator shoppingCartValidator  = mock(ShoppingCartValidator.class);
    private final DiscountService discountService = mock(DiscountService.class);

    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(shoppingCartValidator, discountService);
    
    List<Book> books;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(discountService, "bookPrice", BOOK_PRICE);
        books = new ArrayList<>();
        books.add(new Book("Clean Code", List.of(Author.ROBERT_MARTIN.fullName()), 2008));
        books.add(
                new Book("The Clean Coder", List.of(Author.ROBERT_MARTIN.fullName()), 2011));
        books.add(
                new Book(
                        "Clean Architecture", List.of(Author.ROBERT_MARTIN.fullName()), 2017));
        books.add(
                new Book(
                        "Test Driven Development by Example",
                        List.of(Author.KENT_BECK.fullName()),
                        2003));
        books.add(
                new Book(
                        "Working Effectively With Legacy Code",
                        List.of(Author.MICHAEL_C_FEATHERS.fullName()),
                        2004));
    }

    @Test
    void calculatePrice_whenEmptyCart_then0() {
        ShoppingCart shoppingCart = new ShoppingCart();

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void calculatePrice_whenOneBook_then50() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 1));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(50).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenTwoDifferentBooks_then95() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 1));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(95).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenTwoSameBooks_then100() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 2));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(100).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenThreeDifferentBooks_then135() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(2).getBookId(), 1));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(135).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenTwoSameAndOneDifferentBooks_then145() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 2));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 1));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(145).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenThreeSameBooks_then150() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 3));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(150).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenFourDifferentBooks_then160() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(2).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(3).getBookId(), 1));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(160).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenFourSameBooks_then200() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 4));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(200).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenThreeSameAndOneDifferentBooks_then195() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 3));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(195).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenTwoOneTypeAndTwoAnotherTypeBooks_then190() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 2));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 2));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(190).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenFiveDifferentBooks_then187_5() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(2).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(3).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(4).getBookId(), 1));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(187.5).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenThreeDifferentAndTwoSameBooks_then210() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(2).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(3).getBookId(), 2));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(210).setScale(1, RoundingMode.UP), result);
    }

    @Test
    void calculatePrice_whenFiveSameBooks_then250() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 5));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(250).setScale(1, RoundingMode.UP), result);
    }

    @Test
    @DisplayName("""
                When two books of first type,
                two books of the second type,
                two books of the third type,
                and two different books
                then 320"""
    )
    void calculatePrice() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new CartItem(books.get(0).getBookId(), 2));
        shoppingCart.addItem(new CartItem(books.get(1).getBookId(), 2));
        shoppingCart.addItem(new CartItem(books.get(2).getBookId(), 2));
        shoppingCart.addItem(new CartItem(books.get(3).getBookId(), 1));
        shoppingCart.addItem(new CartItem(books.get(4).getBookId(), 1));

        when(discountService.applyForDiscountRule(any(), any())).thenCallRealMethod();

        BigDecimal result = shoppingCartService.calculatePrice(shoppingCart);

        assertEquals(BigDecimal.valueOf(320).setScale(1, RoundingMode.UP), result);
    }
}