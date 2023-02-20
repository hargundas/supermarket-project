package supermarket;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supermarket {
    private static Inventory inventory;
    private static ShoppingCart shoppingCart;
    private static List<Offer> offers;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public Supermarket(Inventory inventory) {
        Supermarket.inventory = inventory;
        shoppingCart = new ShoppingCart(inventory);
        offers = new ArrayList<>();
    }

    /*
    It takes one or two arguments,
    the first is the inventory.csv file and the second is command file which is input.txt.
    If the second argument is not present, it starts an executeInteractiveCommands function to process user commands.
    Otherwise executeCommandsFromFile is process commands in the file .
     */
    public static void main(String args[]) throws IOException {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java Supermarket <inventory_file> [commands_file]");
            System.exit(1);
        }

        inventory = new Inventory(args[0]);
        shoppingCart = new ShoppingCart(inventory);
        offers = new ArrayList<>();


        //if the inventory file and input file both are exists then if condition executed  otherwise else
        if (args.length == 2) {
            executeCommandsFromFile(args[1]);
        } else {
            executeInteractiveCommands();
        }
    }


    static String executeInteractiveCommands() {
        String result = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            boolean hasNextCommand = scanner.hasNextLine();
            if (!hasNextCommand) {
                return result;
            }
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            System.out.println("$ " + line);
            result = result + "$ " + line + "\n";


            /*
             add   : this commands is used to add the products to shopping cart from inventory
             checkout : this command is used finish the shopping process. if the cart is empty it display cart empty else display done.
             */
            switch (parts[0]) {
                case "add":
                    if (parts.length != 3) {
                        System.out.println("Invalid command: add <product_name> <quantity>");
                        continue;
                    }
                    String productName = parts[1];
                    int quantity;
                    try {
                        quantity = Integer.parseInt(parts[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity");
                        continue;
                    }
                    int addedQuantity = shoppingCart.addProduct(productName, quantity);
                    System.out.println("added " + productName + " " + addedQuantity);
                    result = result + "added " + productName + " " + addedQuantity + "\n";
                    break;


                case "offer":
                    if (parts.length != 3) {
                        System.out.println("Invalid command: offer <offer_type> <product_name>");
                        continue;
                    }
                    break;

                case "checkout":
                    if (shoppingCart.getCartItems().size() == 0) {
                        System.out.println("empty cart");
                        result = result + "empty cart\n";

                    } else {
                        System.out.println("done");
                        result = result + "done";
                    }
                    break;
                default:
                    System.out.println("Invalid command: " + line);
                    result=result+"Invalid command";
                    break;
            }


        }
    }


    static String executeCommandsFromFile(String filename) {
        return "";
    }


}