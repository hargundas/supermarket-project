package supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Supermarket Test Class")
class SupermarketTest {

    private static final String INVENTORY_FILE = "inventory.csv";
    private static final String INPUT = "input.txt";
    String expectedOutput = "$ checkout\n" +
            "empty cart\n" +
            "$ add soap 5\n" +
            "added soap 5\n" +
            "$ add bread 1\n" +
            "added bread 1\n" +
            "$ bill\n" +
            "subtotal:52.50, discount:0.00, total:52.50\n" +
            "$ offer buy_2_get_1_free soap\n" +
            "offer added\n" +
            "$ bill\n" +
            "subtotal:52.50, discount:10.00, total:42.50\n" +
            "$ add soap 1\n" +
            "added soap 1\n" +
            "$ bill\n" +
            "subtotal:62.50, discount:20.00, total:42.50\n" +
            "$ offer buy_1_get_half_off bread\n" +
            "offer added\n" +
            "$ add bread 1\n" +
            "added bread 1\n" +
            "$ bill\n" +
            "subtotal:65.00, discount:21.25, total:43.75\n" +
            "$ checkout\n" +
            "done";

    private Supermarket supermarket;

    private static Inventory inventory;

     @BeforeEach
    void setUp() throws IOException {
        inventory = new Inventory(INVENTORY_FILE);
        supermarket = new Supermarket(inventory);
     }



     @Test
     @DisplayName("Test command ADD for interactive command mode")
     public void testAddExecuteInteractiveCommands() throws IOException{
        String commands = "add soap 5";
         InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
         System.setIn(inputStream);
         String result = supermarket.executeInteractiveCommands();
         assertEquals("$ add soap 5\n" + "added soap 5\n", result);
     }



    @Test
    @DisplayName("Test command Checkout for interactive command mode")
    public void testCheckoutExecuteInteractiveCommands() throws IOException{
        String commands = "checkout";
        InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
        System.setIn(inputStream);
        String result = supermarket.executeInteractiveCommands();
        assertEquals("$ checkout\n" +
                "empty cart\n", result);
    }
    @Test
    @DisplayName("Test command Bill for interactive command mode")
    public void testBillExecuteInteractiveCommands() throws IOException{
        String commands = "bill";
        InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
        System.setIn(inputStream);
        String result = supermarket.executeInteractiveCommands();
        assertEquals("$ bill\n" +
                "subtotal:0.00, discount:0.00, total:0.00\n", result);
    }
    @Test
    @DisplayName("Test command Bill for interactive command mode and add 1 product in item")
    public void testAddProductBillExecuteInteractiveCommands() throws IOException{
        String commands = "add soap 5\n" +
                "bill";
        InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
        System.setIn(inputStream);
        String result = supermarket.executeInteractiveCommands();
        assertEquals("$ add soap 5\n" +
                "added soap 5\n" +
                "$ bill\n" +
                "subtotal:50.00, discount:0.00, total:50.00\n", result);
    }


    @Test
    @DisplayName("Test get non existent product and bill")
    public void testNonExistProductAndBillExecuteInteractiveCommands(){
        String commands = "add egg 5\n" +
                "bill";
        InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
        System.setIn(inputStream);
        String result = supermarket.executeInteractiveCommands();
        assertEquals("$ add egg 5\n" +
                " product not found\n" +
                "$ bill\n" +
                "subtotal:0.00, discount:0.00, total:0.00\n", result);
    }

    @Test
    @DisplayName("invalid input test for interactive command mode")
    public void testInvalidInputExecuteInteractiveCommands() throws IOException {
        String commands = "check\n";

        InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
        System.setIn(inputStream);
        String result = supermarket.executeInteractiveCommands();
        assertEquals("$ check\n" +
                "Invalid command", result);
    }




}