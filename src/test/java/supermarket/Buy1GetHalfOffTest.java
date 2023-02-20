package supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import supermarket.dto.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Buy1GetHalfOff Test Class")
class Buy1GetHalfOffTest {

    @Test
    @DisplayName("Calculate discount for a single item")
    void testCalculateDiscountForSingleItem() {
        List<CartItem> items = new ArrayList<>();
        Product product = new Product("soap", 10.00,100);
        items.add(new CartItem(product, 1));
        Buy1GetHalfOff offer = new Buy1GetHalfOff("soap");
        assertEquals(0.0,Math.abs(offer.calculateDiscount(items)) );
    }

    @Test
    @DisplayName("Calculate discount for multiple items with quantity > 1")
    void testCalculateDiscountForMultipleItemsWithQuantityGreaterThan1() {
        List<CartItem> items = new ArrayList<>();
        Product product = new Product("soap", 10.00,100);
        items.add(new CartItem(product, 2));
        Buy1GetHalfOff offer = new Buy1GetHalfOff("soap");
        assertEquals(5.0,Math.abs(offer.calculateDiscount(items)) );
    }

    @Test
    @DisplayName("Calculate discount for multiple items with quantity = 1")
    void testCalculateDiscountForMultipleItemsWithQuantityEqual1() {
        List<CartItem> items = new ArrayList<>();
        Product product1 = new Product("soap", 10.00,100);
        Product product2 = new Product("bread",2.50,10);
        items.add(new CartItem(product1, 1));
        items.add(new CartItem(product2, 1));
        Buy1GetHalfOff offer = new Buy1GetHalfOff("soap");
        assertEquals(0.0,Math.abs(offer.calculateDiscount(items)) );
    }

    @Test
    @DisplayName("Throw exception when product is not found")
    void testCalculateDiscountWhenProductNotFound() {
        List<CartItem> items = new ArrayList<>();
        Product product  = new Product("soap", 10.00,100);
        items.add(new CartItem(product, 1));
        Buy1GetHalfOff offer = new Buy1GetHalfOff("egg");
        assertThrows(IllegalArgumentException.class, () -> offer.calculateDiscount(items));
    }
}