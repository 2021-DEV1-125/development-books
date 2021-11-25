package co.okizuys.bnp.developmentbooks.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum DiscountRule {
  ALL_UNIQUE(1, 0),
  TWO_UNIQUE(2, 5),
  THREE_UNIQUE(3, 10),
  FOUR_UNIQUE(4, 20),
  FIVE_UNIQUE(5, 25);

  private final int uniqueCount;
  private final int discountPercentage;

  DiscountRule(int uniqueCount, int discountPercentage) {
    this.uniqueCount = uniqueCount;
    this.discountPercentage = discountPercentage;
  }

  public int uniqueCount() {
    return this.uniqueCount;
  }

  public BigDecimal calculatePrice(int number, BigDecimal bookPrice) {
    BigDecimal priceWithoutDiscount = bookPrice.multiply(BigDecimal.valueOf(uniqueCount * number));
    return priceWithoutDiscount.subtract(discount(priceWithoutDiscount));
  }

  private BigDecimal discount(BigDecimal price) {
    return price
        .multiply(BigDecimal.valueOf(discountPercentage))
        .divide(BigDecimal.valueOf(100), 1, RoundingMode.UP);
  }
}
