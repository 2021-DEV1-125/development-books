package co.okizuys.bnp.developmentbooks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

  @Valid private final Set<CartItem> items = new HashSet<>();

  public void addItem(CartItem item) {
    items.add(item);
  }

  public Set<CartItem> getItems() {
    return items;
  }

  @JsonIgnore
  public boolean isEmpty() {
    return items.isEmpty();
  }

  public int size() {
    return items.stream().map(CartItem::getCount).reduce(0, Integer::sum);
  }

  public int length() {
    return items.size();
  }
}
