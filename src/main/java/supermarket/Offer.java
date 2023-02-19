package supermarket;

import java.util.List;

abstract class Offer {

    public abstract double calculateDiscount(List<CartItem> items);


}