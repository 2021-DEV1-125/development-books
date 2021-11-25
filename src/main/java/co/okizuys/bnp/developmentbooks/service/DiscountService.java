package co.okizuys.bnp.developmentbooks.service;

import co.okizuys.bnp.developmentbooks.domain.CartItem;
import co.okizuys.bnp.developmentbooks.domain.DiscountRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DiscountService {

  @Value("${book.price}")
  private BigDecimal bookPrice;

  public BigDecimal applyForDiscountRule(Set<CartItem> cartItems, DiscountRule rule) {
    Map<Long, Integer> bookToCountMap =
        cartItems.stream()
            .sorted(Comparator.comparing(CartItem::getCount).reversed())
            .collect(
                Collectors.toMap(
                    CartItem::getBookId, CartItem::getCount, (e1, e2) -> e2, LinkedHashMap::new));
    return applyForDiscountRule(bookToCountMap, List.of(rule));
  }

  private BigDecimal applyForDiscountRule(
      Map<Long, Integer> bookToCountMapOrigin, List<DiscountRule> discountRules) {

    Map<DiscountRule, Integer> ruleCountMap = new EnumMap<>(DiscountRule.class);
    for (DiscountRule discountRule : discountRules) {
      ruleCountMap = initializeRulesCount();

      Map<Long, Integer> cartBooksToCountMap = new LinkedHashMap<>(bookToCountMapOrigin);

      List<Long> bookIds = getShoppingCartAllBookIds(cartBooksToCountMap);

      for (int i = 0; i < bookIds.size(); i++) {
        if (cartBooksToCountMap.get(bookIds.get(i)) == 0) {
          continue;
        }
        cartBooksToCountMap.put(bookIds.get(i), cartBooksToCountMap.get(bookIds.get(i)) - 1);
        List<Long> boughtBookIds = new ArrayList<>();
        boughtBookIds.add(bookIds.get(i));

        for (int j = i + 1; j < bookIds.size(); j++) {
          Long bookId = bookIds.get(j);

          if (boughtBookIds.size() < discountRule.uniqueCount()
                  && !boughtBookIds.contains(bookId)
                  && isBookInCart(cartBooksToCountMap, bookId)) {
            boughtBookIds.add(bookIds.get(j));
            cartBooksToCountMap.put(bookId, cartBooksToCountMap.get(bookId) - 1);
          }
        }

        if (boughtBookIds.size() < discountRule.uniqueCount()) {
          return applyRulesWithLessUniquenessCount(
                  ruleCountMap, discountRule, cartBooksToCountMap, boughtBookIds);
        } else {
          ruleCountMap.put(discountRule, ruleCountMap.get(discountRule) + 1);
        }
      }
    }
    return calculateWithDiscountRules(ruleCountMap);
  }

  private Map<DiscountRule, Integer> initializeRulesCount() {
    return Arrays.stream(DiscountRule.values())
        .collect(Collectors.toMap(Function.identity(), it -> 0));
  }

  private List<Long> getShoppingCartAllBookIds(Map<Long, Integer> bookToCountMap) {
    return bookToCountMap.entrySet().stream()
        .filter(it -> it.getValue() != 0)
        .flatMap(it -> Collections.nCopies(it.getValue(), it.getKey()).stream())
        .toList();
  }

  private boolean isBookInCart(Map<Long, Integer> bookToCountMap, Long bookId) {
    return bookToCountMap.containsKey(bookId) && bookToCountMap.get(bookId) > 0;
  }

  private BigDecimal applyRulesWithLessUniquenessCount(
      Map<DiscountRule, Integer> ruleCountMap,
      DiscountRule discountRule,
      Map<Long, Integer> bookToCountMap,
      List<Long> boughtBookIds) {
    for (Long bookId : boughtBookIds) {
      bookToCountMap.put(bookId, bookToCountMap.get(bookId) + 1);
    }
    List<DiscountRule> rulesWithLowerUniqueCount = getRulesBeforeCurrentRule(discountRule);
    return calculateWithDiscountRules(ruleCountMap)
        .add(applyForDiscountRule(bookToCountMap, rulesWithLowerUniqueCount));
  }

  private List<DiscountRule> getRulesBeforeCurrentRule(DiscountRule discountRule) {
    List<DiscountRule> allRules = Arrays.asList(DiscountRule.values());
    int ruleIndex = allRules.indexOf(discountRule);
    return allRules.subList(0, ruleIndex);
  }

  private BigDecimal calculateWithDiscountRules(Map<DiscountRule, Integer> rulCountMap) {
    return rulCountMap.entrySet().stream()
        .map(entry -> entry.getKey().calculatePrice(entry.getValue(), bookPrice))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
