package supermarket;

import java.util.List;

abstract class Offer {
    private final String productName;
    public Offer(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public abstract double calculateDiscount(List<CartItem> items);




}