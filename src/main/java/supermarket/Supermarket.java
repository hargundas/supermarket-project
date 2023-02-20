package supermarket;


import java.io.IOException;
import java.util.Scanner;

public class Supermarket {

    public Supermarket() {

    }

        static String executeInteractiveCommands() {
    String result = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
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
                    break;
                case "offer":
                    if (parts.length != 3) {
                        System.out.println("Invalid command: offer <offer_type> <product_name>");
                        continue;
                    }
                    break;
                case "bill":
                    break;
                case "checkout":
                    System.out.println("done");
                    if(parts[0].equals("checkout")){
                        return  "done";
                    }
                    break;
                default:
                    break;
            }


        }
     }


    }