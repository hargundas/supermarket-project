package supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import supermarket.dto.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Buy2Get1Free Test Class")

class Buy2Get1FreeTest {

    @Test
    @DisplayName("Should return 0 discount when no matching product is in cart")
    void testCalculateDiscountWithNoMatchingProduct() {
        // Arrange
        List<CartItem> items = Arrays.asList(
                new CartItem(new Product("soap",10.00,100), 1),
                new CartItem(new Product("bread",2.50,10), 2)
        );
        Buy2Get1Free offer = new Buy2Get1Free("orange");

        // Act
        double discount = Math.abs(offer.calculateDiscount(items));

        // Assert
        assertEquals(0.0, discount);
    }

    @Test
    @DisplayName("Should return 0 discount when only 1 matching product is in cart")
    void testCalculateDiscountWithOneMatchingProduct() {
        // Arrange
        List<CartItem> items = Arrays.asList(
                new CartItem(new Product("soap",10.00,100), 1)
        );
        Buy2Get1Free offer = new Buy2Get1Free("soap");

        // Act
        double discount = Math.abs(offer.calculateDiscount(items));

        // Assert
        assertEquals(0.0, discount);
    }

    @Test
    @DisplayName("Should return 1.5 discount when 3 matching products are in cart")
    void testCalculateDiscountWithThreeMatchingProducts() {
        // Arrange
        List<CartItem> items = Arrays.asList(
                new CartItem(new Product("soap",10.00,100), 3)
        );
        Buy2Get1Free offer = new Buy2Get1Free("soap");

        // Act
        double discount = Math.abs(offer.calculateDiscount(items));

        // Assert
        assertEquals(10, discount);
    }

    @Test
    @DisplayName("Should return 3.0 discount when 6 matching products are in cart")
    void testCalculateDiscountWithSixMatchingProducts() {
        // Arrange
        List<CartItem> items = Arrays.asList(
                new CartItem(new Product("soap",10.00,100), 6)
        );
        Buy2Get1Free offer = new Buy2Get1Free("soap");

        // Act
        double discount = Math.abs(offer.calculateDiscount(items));

        // Assert
        assertEquals(20, discount);
    }


    @Test
    @DisplayName("Should return 0 discount when cart is empty")
    void testCalculateDiscountWithEmptyCart() {
        // Arrange
        List<CartItem> items = new ArrayList<>();
        Buy2Get1Free offer = new Buy2Get1Free("orange");

        // Act
        double discount =Math.abs(offer.calculateDiscount(items)) ;

        // Assert
        assertEquals(0.0, discount);
    }

}