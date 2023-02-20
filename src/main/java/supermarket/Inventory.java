package supermarket;

import supermarket.dto.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
     private final Map<String, Product> products;
     public Inventory(String filename) throws IOException {
          products = new HashMap<>();
          BufferedReader reader = new BufferedReader(new FileReader(filename));
          String line;
          while ((line = reader.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts.length != 3) {
                    throw new IllegalArgumentException("Invalid inventory file format");
               }
               String name = parts[0].trim();
               double price = Double.parseDouble(parts[1].trim());
               int quantity = Integer.parseInt(parts[2].trim());
               Product product = new Product(name, price,quantity);
               products.put(name, product);
          }
          reader.close();
     }

     public Product getProduct(String name) {
          return products.get(name);
     }

}
