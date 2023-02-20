package supermarket;

import java.util.List;

class Buy2Get1Free extends Offer {

    private Inventory inventory;

    public Buy2Get1Free(String productName) {
        super(productName);
    }

    @Override
    public double calculateDiscount(List<CartItem> items) {
        int quantity = 0;
        double price = 0.0;
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(getProductName())) {
                quantity += item.getQuantity();
                price = item.getProduct().getPrice();
            }
        }

        return (quantity / 3) * price * -1;
    }


}
