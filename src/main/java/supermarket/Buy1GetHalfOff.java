package supermarket;

import java.util.List;


class Buy1GetHalfOff extends Offer {
    public Buy1GetHalfOff(String productName) {
        super(productName);
    }


    @Override
    public double calculateDiscount(List<CartItem> items) {
        double discount = 0;
        boolean productFound = false;
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(getProductName())) {
                productFound = true;
                if (item.getQuantity() > 1) {
                    discount += (item.getQuantity() / 2) * (item.getProduct().getPrice() / 2);
                }
            }
        }
        if (!productFound) {
            throw new IllegalArgumentException("Product not found: " + getProductName());
        }
        return discount * -1;
    }}