// tracks purchase details

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

    public void addItemsFromCart(List<CartItem> cartItems) {
        for (CartItem item : cartItems) {
            this.items.add(item);
            this.total += item.getProduct().getPrice() * item.getQuantity();
        }
    }

    public void clear() {
        this.items.clear();
        this.total = 0.0;
    }

    public void printReceipt() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";

        System.out.println(ANSI_GREEN + "RECEIPT:" + ANSI_RESET);
        for (CartItem item : items) {
            System.out.println(ANSI_YELLOW + item.getProduct().getName() + ANSI_RESET +
                    ", Quantity: " + ANSI_RED + item.getQuantity() + ANSI_RESET +
                    ", Cost: " + ANSI_RED + "$" + (item.getProduct().getPrice() * item.getQuantity()) + ANSI_RESET);
        }
        System.out.println(ANSI_GREEN + "Total: $" + total + ANSI_RESET);
    }
}
