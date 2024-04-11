public class Main {
    public static void main(String[] args) {
// Initialize Inventory (which loads products from CSV)
        Inventory stock = new Inventory();
        System.out.println("Initial Inventory Loaded:");
        stock.listProducts();

        // Initialize the OrderProcessor with the loaded Inventory
        OrderProcessor orderProcessor = new OrderProcessor(stock);

        // Example of adding a product to a purchase
        System.out.println("\nAttempting to add products to a purchase...");
        boolean appleAdded = orderProcessor.addToPurchase(1, 2); // Assuming IDs and quantities
        boolean orangeAdded = orderProcessor.addToPurchase(2, 1);

        if (appleAdded && orangeAdded) {
            System.out.println("Products added to purchase successfully.");
        } else {
            System.out.println("Failed to add some products to the purchase.");
        }

        // Finalize Purchase and Print Receipt
        System.out.println("\nFinalizing purchase and printing receipt...");
        orderProcessor.finalizePurchase();

        // Show updated Inventory after purchase
        System.out.println("\nInventory after purchase:");
        stock.listProducts();

        Menu mainMenu = new MainMenu(stock); // initialized a new main menu
        while (mainMenu != null) {
            mainMenu.displayMenu();
            mainMenu = mainMenu.handleUserInput();
        }

        System.out.println("Goodbye!!");
    }
}