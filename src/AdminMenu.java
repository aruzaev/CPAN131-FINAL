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

        int choice = Utility.getUserChoice();

        switch(choice) {
            case 1:
                addProduct();
                break;
            case 2:
         //       removeProduct();
                break;
            case 3:
           //     updateProduct();
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

    private void listProducts() {
        stock.listProducts();
    }

}


