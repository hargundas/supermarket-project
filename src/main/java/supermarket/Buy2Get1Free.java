package supermarket;

import java.util.List;

class Buy2Get1Free extends Offer {
    @Override
    public double calculateDiscount(List<CartItem> items) {
        return 0;
    }
}
