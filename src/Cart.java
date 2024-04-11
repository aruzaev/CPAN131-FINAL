import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items; // list of items that have been added to the cart

    public Cart() {
        this.items = new ArrayList<>();
    }

    // add product to the cart. If it already exists, increase quantity
    public void addItem(Product product, int quantity) {
        for (CartItem item : items) { // for each item inside of the items list
            if (item.getProduct().equals(product)) { // direct object comparison
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity)); // adds final product and items
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("The cart is empty");
            return;
        }

        System.out.println("Cart:");
        for (CartItem item : items) {
            System.out.println(item.getProduct().getName() + ", Quantity: " + item.getQuantity());
        }

    }

}
