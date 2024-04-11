public class AdminMenu implements Menu{
    private Inventory stock;

    public AdminMenu(Inventory stock) {
        this.stock = stock;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nAdmin menu");
        System.out.println("==============");
        System.out.println("1. Edit Products");
        System.out.println("2. Edit Users");
        System.out.println("3. Back to main menu");
        System.out.println("0. Exit");
    }

    @Override
    public Menu handleUserInput() {
        System.out.println("Enter the choice: ");
        int choice = Utility.getUserChoice();

        switch(choice) {
            case 1:
               return new ProductManipulation(stock);
            case 2:
//                new UserManipulation();
                return new UserManipulation(stock);
            case 3:
                updateProduct();
                break;
            case 4:
                listProducts();
                break;
            case 5:
                return new MainMenu(stock); // go back to main menu
            case 0:
                System.out.println("See you soon!");
                System.exit(0);
            default:
                System.out.println("Invalid choice, try again.");
        }
        return this; // stay in the admin menu
    }

    // TODO: Figure out a way to not make this so long, (maybe add all of these methods to the utility class?)
    private void addProduct() {
        ProductValidation validation = new ProductValidation(stock);
        String id;
        String name;
        String description;
        String category;
        String price;
        String quantity;
        String[] errors = new String[4]; // Maximum possible number of errors

        System.out.println("Enter product information separated by commas \n(ID,Name,Description,Category,Price,Quantity):");
        String[] parts = (Utility.getUserInput()).split(",");

        if (parts.length == 6) {
            //Defines variables by applying the appropriate array part
            id = parts[0];
            name = parts[1];
            description = parts[2];
            category = parts[3];
            price = parts[4];
            quantity = parts[5];

            int errorCount = 0; // Counter for the number of errors

            if (!validation.isValidId(id)) {
                errors[errorCount++] = "Invalid ID. ID must be unique and a positive integer.";
            }
            if (!validation.isValidCategory(category)) {
                errors[errorCount++] = "Invalid category. Must be one of: Pharmacy, Grocery, Electronics, Toys, Misc";
            }
            if (!validation.isValidPrice(price)) {
                errors[errorCount++] = "Invalid price. Price must be a positive number.";
            }
            if (!validation.isValidQuantity(quantity)) {
                errors[errorCount++] = "Invalid quantity. Quantity must be a non-negative integer.";
            }

            if (errorCount > 0) {
                System.out.println("Errors:");
                for (int i = 0; i < errorCount; i++) {
                    System.out.println("- " + errors[i]);
                }
                addProduct();
            } else {
                // All input is valid, add the product
                stock.addProduct(new Product(Integer.parseInt(id), name, description, category,  Integer.parseInt(quantity), Double.parseDouble(price)));
                System.out.println("Product added successfully!");
            }
        } else {
            System.out.println("Input format is incorrect. Please provide the information separated by commas.");
            System.out.println("Try again?");
            if(Utility.tryAgain()){
                addProduct();
            }
        }
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


