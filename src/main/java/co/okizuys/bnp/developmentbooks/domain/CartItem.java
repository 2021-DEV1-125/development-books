package co.okizuys.bnp.developmentbooks.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Min;

public class CartItem {

  private long bookId;

  @Min(1)
  private int count;

  public CartItem(long bookId, int count) {
    this.bookId = bookId;
    this.count = count;
  }

  public long getBookId() {
    return bookId;
  }

  public int getCount() {
    return count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    CartItem cartItem = (CartItem) o;

    return new EqualsBuilder().append(bookId, cartItem.bookId).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(bookId).toHashCode();
  }
}
