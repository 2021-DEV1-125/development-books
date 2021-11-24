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
}
