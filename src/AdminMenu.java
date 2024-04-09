public class AdminMenu implements Menu{
    private Inventory stock;

    public AdminMenu(Inventory stock) {
        this.stock = stock;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nAdmin menu");
        System.out.println("\n============");
        System.out.println("1. Add product");
        System.out.println("2. Remove product");
        System.out.println("3. Update product");
        System.out.println("4. List all products");
        System.out.println("5. Back to main menu");
        System.out.println("0. Exit");
    }

    @Override
    public Menu handleUserInput() {
        System.out.println("Enter the choice: ");
        int choice = Utility.getUserChoice();

        switch(choice) {
            case 1:
                addProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                listProducts();
                break;
            case 5:
                return new MainMenu(stock); // go back to main menu
            default:
                System.out.println("Invalid choice, try again.");
        }
        return this; // stay in the admin menu
    }

    // TODO: Figure out a way to not make this so long, (maybe add all of these methods to the utility class?)
    // TODO: Implement only one output to prompt the user for input (ex. "Enter the item delimited with , (id, name, description, price, quantity)
    private void addProduct() {
        System.out.println("Enter the product details to add a new product:");
        System.out.println("ID: ");
        int id = Utility.getUserChoice();
        System.out.println("Name: ");
        String name = Utility.getUserInput();
        System.out.println("Description: ");
        String description = Utility.getUserInput();
        System.out.println("Price: ");
        double price = Utility.getUserDouble();
        System.out.println("Quantity: ");
        int quantity = Utility.getUserChoice();

        Product product = new Product(id, name, description, quantity, price);
        stock.addProduct(product);
        System.out.println("Product added successfully!");
    }

    private void removeProduct() {
        listProducts();
        System.out.println("===========");
        System.out.println("Enter the product ID to remove");
        int id = Utility.getUserChoice();
        stock.removeProduct(id);
        System.out.println("Product removed successfully!");
    }

    private void updateProduct() {
        listProducts();
        System.out.println("==========");
        System.out.println("Enter the product ID to update: ");
        int id = Utility.getUserChoice();

        System.out.println("Enter the new name (Press enter to keep current)");
        String newName = Utility.getUserInput();

        System.out.println("Enter the new description (Press enter to keep current)");
        String newDescription = Utility.getUserInput();

        // TODO: make the current check better?
        System.out.println("Enter the new price (negative to keep current)");
        double newPrice = Utility.getUserDouble();

        System.out.println("Enter the new quantity (negative to keep current)");
        int newQuantity = Utility.getUserChoice();

        // if its empty then nullify it else keep it
        newName = newName.isEmpty() ? null : newName;
        newDescription = newDescription.isEmpty() ? null : newDescription;

        newPrice = newPrice < 0 ? null : newPrice;
        newQuantity = newQuantity < 0 ? null : newQuantity;

        stock.updateProduct(id, newName, newDescription, newPrice, newQuantity);
    }



    private void listProducts() {
        stock.listProducts();
    }

}


