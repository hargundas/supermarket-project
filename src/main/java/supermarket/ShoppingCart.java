package supermarket;


import supermarket.dto.Product;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    private Inventory inventory;
    private List<CartItem> cartItems;

    public ShoppingCart(Inventory inventory) {
        this.inventory = inventory;
        cartItems = new ArrayList<>();
    }

    public int addProduct(String name, int quantity) {
        Product product = inventory.getProduct(name);
        if (product == null ) {
            System.out.println("Product Not Found");
            return  0;
        }
        if(quantity < 0 || quantity == 0){
            System.out.println("Quantity can not be less than 0");
            return  0;
        }

        int addedQuantity = updateCartItem(product, quantity);
        if (addedQuantity < quantity) {
            System.out.println("Not all items could be added to the cart for " + name);
        }
        return addedQuantity;
    }

    private int updateCartItem(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                int quantityToAdd = Math.min(quantity, item.getAvailableQuantity());
                item.addQuantity(quantityToAdd);
                return quantityToAdd;
            }
        }
        CartItem newItem = new CartItem(product, quantity);
        cartItems.add(newItem);
        return quantity;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }


    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }
}
