import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<CartItem> items;
    private double total;

    public Receipt() {
        this.items = new ArrayList<>();
        this.total = 0.0;
    }

    // debugging purposes
    public void addItem(Product product, int quantity) {
        this.items.add(new CartItem(product, quantity));
        this.total += product.getPrice() * quantity;
    }
    // add items to the cart and adding the price and quantity
    public void addItemsFromCart(List<CartItem> cartItems) {
        for (CartItem item : cartItems) {
            this.items.add(item);
            this.total += item.getProduct().getPrice() * item.getQuantity();
        }
    }

    // clearing the cart and total
    public void clear() {
        this.items.clear();
        this.total = 0.0;
    }

    public void printReceipt() {
        // printing with color
        System.out.println(Utility.ANSI_GREEN + "RECEIPT:" + Utility.ANSI_RESET);
        for (CartItem item : items) {
            System.out.println(Utility.ANSI_YELLOW + item.getProduct().getName() + Utility.ANSI_RESET +
                    ", Quantity: " + Utility.ANSI_RED + item.getQuantity() + Utility.ANSI_RESET +
                    ", Cost: " + Utility.ANSI_RED + "$" + (item.getProduct().getPrice() * item.getQuantity()) + Utility.ANSI_RESET);
        }
        System.out.println(Utility.ANSI_GREEN + "Total: $" + total + Utility.ANSI_RESET);
    }
}
