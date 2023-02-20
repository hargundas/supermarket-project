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
            int addedQuantity = shoppingCart.addProduct("invalid_product", 2);
            assertEquals(0, addedQuantity);
        }

        @Test
        @DisplayName("Adding negative quantity of product to cart should return 0")
        void testInvalidQuantityAddProduct() {
            ShoppingCart shoppingCart = new ShoppingCart(inventory);
            int addedQuantity = shoppingCart.addProduct("soap", -2);
            assertEquals(0, addedQuantity);
        }







    }
