package supermarket;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
             bill :this command calculates the total price of the shopping cart.
             offer :  command adds an offer for a product.
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
                    if (addedQuantity == 0) {
                        result = result + " product not found\n";
                    } else {
                        System.out.println("added " + productName + " " + addedQuantity);
                        result = result + "added " + productName + " " + addedQuantity + "\n";

                    }
                    break;


                case "bill":
                    double subtotal = shoppingCart.calculateTotalPrice();
                    double discount = calculateDiscount();
                    double total = subtotal - discount;
                    System.out.println("subtotal:" + DECIMAL_FORMAT.format(subtotal) +
                            ", discount:" + DECIMAL_FORMAT.format(discount) +
                            ", total:" + DECIMAL_FORMAT.format(total));
                    result = result + "subtotal:" + DECIMAL_FORMAT.format(subtotal) +
                            ", discount:" + DECIMAL_FORMAT.format(discount) +
                            ", total:" + DECIMAL_FORMAT.format(total) + "\n";
                    break;
                case "offer":
                    if (parts.length != 3) {
                        System.out.println("Invalid command: offer <offer_type> <product_name>");
                        continue;
                    }
                    String offerType = parts[1];
                    productName = parts[2];
                    Optional<Offer> existingOffer = offers.stream()
                            .filter(o -> o.getProductName().equals(productName))
                            .findFirst();
                    if (existingOffer.isPresent()) {
                        System.out.println("Removing previous offer for " + productName);
                        offers.remove(existingOffer.get());
                    }
                    Offer offer;
                    switch (offerType) {
                        case "buy_2_get_1_free":
                            offer = new Buy2Get1Free(productName);
                            offers.add(offer);
                            System.out.println("offer added");
                            result= result+"offer added\n";
                            break;
                        case "buy_1_get_half_off":
                            offer = new Buy1GetHalfOff(productName);
                            offers.add(offer);
                            System.out.println("offer added");
                            result= result+"offer added\n";
                            break;
                        default:
                            System.out.println("Invalid offer type: " + offerType);
                    }
                    break;

                case "checkout":
                    if (shoppingCart.getCartItems().size() == 0) {
                        System.out.println("empty cart");
                        result = result + "empty cart\n";

                    } else {
                        System.out.println("done");
                       return result = result + "done";
                    }
                    break;
                default:
                    System.out.println("Invalid command: " + line);
                    result = result + "Invalid command";
                    break;
            }


        }
    }


    static String executeCommandsFromFile(String filename) throws IOException{
        String result ="";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            System.out.println("$ "+line);
            result =result+"$ "+line+"\n";
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
                    result = result+"added " + productName + " " + addedQuantity+"\n";
                    break;

                case "offer":
                    if (parts.length != 3) {
                        System.out.println("Invalid command: offer <offer_type> <product_name>");
                        continue;
                    }
                    String offerType = parts[1];
                    productName = parts[2];
                    Optional<Offer> existingOffer = offers.stream()
                            .filter(o -> o.getProductName().equals(productName))
                            .findFirst();
                    if (existingOffer.isPresent()) {
                        System.out.println("Removing previous offer for " + productName);
                        offers.remove(existingOffer.get());
                    }
                    Offer offer;
                    switch (offerType) {
                        case "buy_2_get_1_free":
                            offer = new Buy2Get1Free(productName);
                            offers.add(offer);
                            System.out.println("offer added");
                            result= result+"offer added\n";
                            break;
                        case "buy_1_get_half_off":
                            offer = new Buy1GetHalfOff(productName);
                            offers.add(offer);
                            System.out.println("offer added");
                            result= result+"offer added\n";
                            break;
                        default:
                            System.out.println("Invalid offer type: " + offerType);
                    }
                    break;

                case "bill":
                    double subtotal = shoppingCart.calculateTotalPrice();
                    double discount = calculateDiscount();
                    double total = subtotal - discount;
                    System.out.println("subtotal:" + DECIMAL_FORMAT.format(subtotal) +
                            ", discount:" + DECIMAL_FORMAT.format( discount) +
                            ", total:" + DECIMAL_FORMAT.format(total));
                    result= result+"subtotal:" + DECIMAL_FORMAT.format(subtotal) +
                            ", discount:" + DECIMAL_FORMAT.format( discount) +
                            ", total:" + DECIMAL_FORMAT.format(total)+"\n";
                    break;

                case "checkout":
                    if(shoppingCart.getCartItems().size() ==0){
                        System.out.println("empty cart");
                        result=result+"empty cart\n";
                    }else {
                        System.out.println("done");
                        result=result+"done";
                    }
                    break;
                default:
                    System.out.println("Invalid command: " + line);
                    result=result+"Invalid command\n";
                    break;
            }
        }
        return result;
    }
    public static double calculateDiscount() {
        double discount = 0;
        List<CartItem> cartItems = shoppingCart.getCartItems();
        for (Offer offer : offers) {
            discount += offer.calculateDiscount(cartItems);
        }
        return Math.abs(discount);
    }

}