// responsible for managing stock levels

import java.util.ArrayList;

public class Inventory {
    // Using ArrayList instead of a regular array to allow for dynamic resizing
    private ArrayList<Product> products;

    public Inventory() {
        // Initialize the ArrayList to store Product objects
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        // Add a new product to the inventory
        products.add(product);
    }

    // TODO: Add exceptions for getProduct instead of returning as null?
    // TODO: figure out a use for this
    public Product getProduct(int productID) {
        // Search for a product in the inventory by its ID
        // If found, return the product, otherwise return null
        for (Product product : products) {
            if (product.getId() == productID) {
                return product;
            }
        }
        return null;
    }

    // TODO: see if can call getProduct here
    public void removeProduct(int productID) {
        // Remove a product from the inventory by its ID
        // If the product is not found, print an error message
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productID) {
                products.remove(i);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void listProducts() {
        // Print out all the products in the inventory
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public ArrayList<Product> getProducts() {
        // Return the ArrayList of products
        return products;
    }

    public void updateProduct(int productID, String newName, String newDescription, double newPrice, int newQuantity) {
        // Update a product's details in the inventory by its ID
        // If the product is not found, print an error message
        for (Product product : products) {
            if (product.getId() == productID) {
                if (newName != null && !newName.isEmpty()) product.setName(newName);
                if (newDescription != null) product.setDescription(newDescription);
                if (newPrice >= 0) product.setPrice(newPrice);
                if (newQuantity >= 0) product.setQuantity(newQuantity);

                System.out.println("Product updated successfully.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public boolean decreaseQuantity(int productID, int quantityBeingSold) {
        Product product = getProduct(productID);
        if (product != null && product.getQuantity() >= quantityBeingSold) {
            product.setQuantity(product.getQuantity() - quantityBeingSold);
            return true;
        } else {
            System.out.println("Not enough stock for product ID " + productID   );
            return false;
        }
    }
}