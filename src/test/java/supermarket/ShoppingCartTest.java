package supermarket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("ShoppingCart Test class")
class ShoppingCartTest {
         private static Inventory inventory;

        @BeforeAll
        static void setUp() throws IOException {
            inventory = new Inventory("inventory.csv");
        }


        @Test
        @DisplayName("Adding valid product to cart should return the correct quantity")
        void testAddProduct() {
            ShoppingCart shoppingCart = new ShoppingCart(inventory);
            int addedQuantity = shoppingCart.addProduct("soap", 2);
            assertEquals(2, addedQuantity);
        }

        @Test
        @DisplayName("Adding invalid product to cart should return 0")
        void testInvalidAddProduct() {
            ShoppingCart shoppingCart = new ShoppingCart(inventory);
            int addedQuantity = shoppingCart.addProduct("egg", 2);
            assertEquals(0, addedQuantity);
        }

        @Test
        @DisplayName("Adding negative quantity of product to cart should return 0")
        void testInvalidQuantityAddProduct() {
            ShoppingCart shoppingCart = new ShoppingCart(inventory);
            int addedQuantity = shoppingCart.addProduct("soap", -2);
            assertEquals(0, addedQuantity);
        }



    @Test
    @DisplayName("Calculating total price of cart should return the correct amount")
    void testCalculateTotalPrice() {
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        shoppingCart.addProduct("soap", 2);
        shoppingCart.addProduct("bread", 3);
        double totalPrice = shoppingCart.calculateTotalPrice();
        assertEquals(27.5, totalPrice);
    }




    @Test
    @DisplayName("Calculating discount for valid product in cart should return the correct amount")
    void testCalculateDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        shoppingCart.addProduct("soap", 5);
        shoppingCart.addProduct("bread", 1);
        Offer offer = new Buy2Get1Free("soap");
        double discount = offer.calculateDiscount(shoppingCart.getCartItems());

        assertEquals(10, Math.abs(discount));
    }


    @Test
    @DisplayName("Calculating discount for invalid product in cart should return 0")
    void testCalculateDiscountWithInvalidProduct() {
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        shoppingCart.addProduct("soap", 2);
        shoppingCart.addProduct("bread", 3);
        Offer offer = new Buy2Get1Free("invalid_product");
        double discount = offer.calculateDiscount(shoppingCart.getCartItems());
        assertEquals(0,Math.abs(discount));
    }

    @Test
    @DisplayName("Calculating discount for empty cart should return 0")
    void testCalculateDiscountWithNoProducts() {
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        Offer offer = new Buy2Get1Free("soap");
        double discount = offer.calculateDiscount(shoppingCart.getCartItems());
        assertEquals(0,Math.abs(discount));
    }
    //
    @Test
    @DisplayName("Calculating total price of cart with offer should return the correct amount")
    void testCalculateTotalPriceWithOffer() {
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        shoppingCart.addProduct("soap", 3);
        Offer offer = new Buy2Get1Free("soap");
        double totalPrice = shoppingCart.calculateTotalPrice();
        double  discount = offer.calculateDiscount(shoppingCart.getCartItems());
        totalPrice=totalPrice- Math.abs(discount);
        assertEquals(20.0, totalPrice, 0.001);
    }

    }
