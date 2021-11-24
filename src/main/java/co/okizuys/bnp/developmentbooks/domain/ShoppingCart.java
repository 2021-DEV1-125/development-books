package co.okizuys.bnp.developmentbooks.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }
}
