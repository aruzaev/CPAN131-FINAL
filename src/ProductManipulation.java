import java.util.Arrays;

public class ProductManipulation implements Menu{
    private Inventory stock;

    public ProductManipulation(Inventory stock) {
        this.stock = stock;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nProduct Manipulation");
        System.out.println("===================");
        System.out.println("1. Add product");
        System.out.println("2. Remove product");
        System.out.println("3. Update product");
        System.out.println("4. List all products");
        System.out.println("5. Back to main menu");
        System.out.println("6. Back to Admin menu");
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
            case 6:
                return new AdminMenu(stock); // go back to Admin Menu
            case 0:
                System.out.println("See you soon!");
                System.exit(0);
            default:
                System.out.println("Invalid choice, try again.");
        }
        return this; // stay in the product manipulation menu
    }

    private void addProduct() {
        ProductValidation validation = new ProductValidation(stock);
        String id;
        String name;
        String description;
        String category;
        String price;
        String quantity;
        String[] errors = new String[4]; // Maximum possible number of errors

        System.out.println("=====Add Product=====");
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
                errors[errorCount++] = "Invalid category. Must be one of: " + Arrays.toString(Utility.validCategories);
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

    private void updateProduct() {
        listProducts();
        System.out.println("=====Update Product=====");
        System.out.println("Enter the product ID to update:");
        int id = Utility.getUserChoice();

        Product existingProduct = stock.getProduct(id);
        if (existingProduct == null) {
            System.out.println("Product with ID " + id + " not found.");
            return;
        }

        // initializiing variables
        String newName = existingProduct.getName();
        String newDescription = existingProduct.getDescription();
        double newPrice = existingProduct.getPrice();
        int newQuantity = existingProduct.getQuantity();

        // confirming new inputs
        System.out.println("Current name: " + existingProduct.getName());
        if (Utility.confirm("Do you want to update the name? (yes/no)")) {
            System.out.println("Enter the new name:");
            newName = Utility.getUserInput();
        }

        System.out.println("Current description: " + existingProduct.getDescription());
        if (Utility.confirm("Do you want to update the description? (yes/no)")) {
            System.out.println("Enter the new description:");
            newDescription = Utility.getUserInput();
        }

        System.out.println("Current price: " + existingProduct.getPrice());
        if (Utility.confirm("Do you want to update the price? (yes/no)")) {
            System.out.println("Enter the new price:");
            newPrice = Utility.getUserDouble();
        }

        System.out.println("Current quantity: " + existingProduct.getQuantity());
        if (Utility.confirm("Do you want to update the quantity? (yes/no)")) {
            System.out.println("Enter the new quantity:");
            newQuantity = Utility.getUserChoice();
        }

        // Only after all potential changes have been confirmed do we call the update method.
        stock.updateProduct(id, newName, newDescription, newPrice, newQuantity);
        System.out.println("Product updated successfully!");
    }


    private void removeProduct() {
        listProducts();
        System.out.println("=====Remove Product=====");
        System.out.println("Enter the product ID to remove");
        int id = Utility.getUserChoice();
        stock.removeProduct(id);
        System.out.println("Product removed successfully!");
    }

    private void listProducts() {
        stock.listProducts();
    }

}

