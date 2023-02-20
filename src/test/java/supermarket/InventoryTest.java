package supermarket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import supermarket.dto.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("InventoryTest class")
class InventoryTest {

    private Inventory inventory;
    private static final String INVALID_TEST_FILE_NAME = "inventory1.csv";

    @BeforeEach
    void setUp() throws IOException {
    }


    @ParameterizedTest
    @ValueSource(strings = {"inventory.csv"})
    @DisplayName("test valid inventory file read")
    void testValidFileReadInventory(String filename) throws IOException {
        inventory = new Inventory(filename);
        assertNotNull(inventory.getProduct("soap"));
        assertNotNull(inventory.getProduct("bread"));
        assertNull(inventory.getProduct("nonexistent product"));

        assertEquals(100, inventory.getProduct("soap").getQuantity());
        assertEquals(10, inventory.getProduct("soap").getPrice());
        assertEquals(10, inventory.getProduct("bread").getQuantity());
        assertEquals(2.50, inventory.getProduct("bread").getPrice());

    }

    @ParameterizedTest
    @ValueSource(strings = {"inventory1.csv"})
    @DisplayName("test invalid inventory file read")
    void testInvalidFileReadInventory(String invalidFileName) throws IOException {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            new Inventory(invalidFileName);
        });

    }

    @ParameterizedTest
    @ValueSource(strings = {"bread"})
    @DisplayName("Valid Get Product Test")
    void testGetProduct(String productName) throws IOException {
        inventory = new Inventory("inventory.csv");
        Product product = inventory.getProduct(productName);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(productName, product.getName());
        Assertions.assertEquals(2.5, product.getPrice());
        Assertions.assertEquals(10, product.getQuantity());
    }

    @ParameterizedTest
    @ValueSource(strings = {"egg"})
    void testGetNonExistentProduct(String productName) throws IOException {
        inventory = new Inventory("inventory.csv");
        Product product = inventory.getProduct(productName);
        Assertions.assertNull(product);
    }

    @Test
    void testInvalidInventoryFile() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            new Inventory(INVALID_TEST_FILE_NAME);
        });
    }
}

