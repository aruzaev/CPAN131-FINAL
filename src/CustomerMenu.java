public class CustomerMenu implements Menu {
    private Inventory stock;
    private Cart cart;
    private LogInManager customer;

    public CustomerMenu(Inventory stock) {
        this.stock = stock;
        this.cart = new Cart();
        this.customer = customer;
    }

    @Override
    public void displayMenu() {
        System.out.println("Welcome, " + Utility.username);
        System.out.println("\nCustomer menu");
        System.out.println("\n============");
        System.out.println("1. View products");
        System.out.println("2. View cart");
        System.out.println("3. Checkout");
        System.out.println("0. Back to main menu");
    }

    @Override
    public Menu handleUserInput() {
        System.out.println("Enter choice: ");
        int choice = Utility.getUserChoice();
        switch (choice) {
            case 1:
                viewProducts();
                break;
            case 2:
                viewCart();
                break;
            case 3:
                checkout();
                break;
            case 0:
                return new MainMenu(stock);
            default:
                System.out.println("Invalid choice. Try again.");
        }
        return this;
    }

    private void viewProducts() {
        stock.listProducts();
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

    private void viewCart() {
        cart.displayCart();
    }

    private void checkout() {
        // Implement checkout logic, e.g., generate receipt, update inventory, etc.
        System.out.println("Checkout not implemented yet.");
    }
}
