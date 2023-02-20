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


    @BeforeEach
    void setUp() throws IOException {
     }

    @Test
    @DisplayName("valid input test for interactive command mode")
    public void testExecuteInteractiveCommands() throws IOException {
        String commands = "checkout\n" +
                "add soap 5\n" +
                "add bread 1\n" +
                "bill\n" +
                "offer buy_2_get_1_free soap\n" +
                "bill\n" +
                "add soap 1\n" +
                "bill\n" +
                "offer buy_1_get_half_off bread\n" +
                "add bread 1\n" +
                "bill\n" +
                "checkout\n";

        InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
        System.setIn(inputStream);
        String result = supermarket.executeInteractiveCommands();
        assertEquals("done", result);
    }
}