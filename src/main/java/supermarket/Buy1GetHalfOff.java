package supermarket;

import java.util.List;

class Buy1GetHalfOff extends Offer {

    @Override
    public double calculateDiscount(List<CartItem> items) {
        return 0;
    }
}