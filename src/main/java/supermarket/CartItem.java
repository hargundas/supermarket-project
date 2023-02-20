package supermarket;


import supermarket.dto.Product;

public class CartItem {

    private Product product;
    private int quantity;
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getAvailableQuantity() {
        return product.getQuantity();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CartItem)) {
            return false;
        }
        CartItem other = (CartItem) obj;
        return product.equals(other.product);
    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }

}
