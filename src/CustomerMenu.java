public class CustomerMenu implements Menu {
    private Inventory stock; // Holds reference to the inventory
    private Cart cart; // Shopping cart for customer
    private LogInManager customer; // Manages the login state

    // Constructor initializes the menu, stock, and cart
    public CustomerMenu(Inventory stock) {
        this.stock = stock;
        this.cart = new Cart();
        this.customer = customer;
        // Greeting message with the logged-in username
        System.out.println("\nWelcome, "+ Utility.ANSI_GREEN + Utility.username + Utility.ANSI_RESET);
    }

    // Display options available for the customer
    @Override
    public void displayMenu() {
        System.out.println("\nCustomer menu");
        System.out.println("================" );
        System.out.println("1. View all products");
        System.out.println("2. View products by category");
        System.out.println("3. View cart");
        System.out.println("4. Checkout");
        System.out.println("0. Back to main menu");
    }

    // Handle user input from the customer menu
    @Override
    public Menu handleUserInput() {
        System.out.println("Enter choice: ");
        int choice = Utility.getUserChoice();
        switch (choice) {
            case 1:
                viewAllProducts();
                break;
            case 2:
                viewProductsByCategory();
                break;
            case 3:
                viewCart();
                break;
            case 4:
                checkout();
                break;
            case 0:
                return new MainMenu(stock);
            default:
                System.out.println("Invalid choice. Try again.");
        }
        return this;
    }

    // Displays all products and allows adding to cart
    private void viewAllProducts() {
        stock.listProducts();
        addToCart();
    }

    // Allows customer to view products by specified categories
    private void viewProductsByCategory(){
        System.out.println("\nSelect Category");
        System.out.println("==============");
        System.out.println("1. Pharmacy");
        System.out.println("2. Grocery");
        System.out.println("3. Electronics");
        System.out.println("4. Toys");
        System.out.println("0. Back");
        switch (Utility.getUserChoice()) {
            case 1:
                stock.displayProductsByCategory("Pharmacy");
                break;
            case 2:
                stock.displayProductsByCategory("Grocery");
                break;
            case 3:
                stock.displayProductsByCategory("Electronics");
                break;
            case 4:
                stock.displayProductsByCategory("Toys");
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
        addToCart();
    }

    // Helper method to add products to the shopping cart
    private void addToCart() {
        System.out.println("Enter the product ID to add to cart (0 to cancel): ");
        int productId = Utility.getUserChoice();
        if (productId != 0) {
            Product product = stock.getProduct(productId);
            if (product != null) {
                System.out.println("Enter the quantity: ");
                int quantity = Utility.getUserChoice();
                cart.addItem(product, quantity);
                System.out.println("Product added to cart.");
            } else {
                System.out.println("Product not found.");
            }
        }
    }

    // Display the contents of the cart
    private void viewCart() {
        cart.displayCart();
    }

    // Handles the checkout process including inventory updates and payment
    private void checkout() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Proceeding to checkout...");
        boolean isStockUpdated = true; // flag to see if the stock has been impacted

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            boolean updateSuccess = stock.decreaseQuantity(product.getId(), quantity);
            if (!updateSuccess) {
                System.out.println("Could not complete transaction: Insufficient Stock");
                isStockUpdated = false;
                break;
            }
        }

        if (isStockUpdated) {
            Receipt receipt = new Receipt();
            receipt.addItemsFromCart(cart.getItems());
            receipt.printReceipt();

            cart.clearCart();
            System.out.println("Checkout successful. Thank you for your purchase!");
        } else {
            System.out.println("Checkout failed due to stock issues. Please review your cart.");
        }
    }
}
