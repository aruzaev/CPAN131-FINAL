public class Inventory {
    private Product[] products;
    private int count; // keeps track of the count of items in the inventory

    public Inventory(int size) { // inventory constructor
        products = new Product[size];
        count = 0; // resets count to 0

    }

    public void addProduct(Product product) {
        if (count < products.length) { // is the inventory full
            products[count] = product; // adding the newly created product to the products array
            count++; // increasing id of inventory

        } else {
            System.out.println("Inventory is full!");
        }
    }

    // TODO: Add exceptions for getProduct instead of returning as null
    public Product getProduct(int productID) {
        for (int i = 0; i < count; i++) {
            if (products[i].getId() == productID) {
                return products[i]; // if id has been found get the element space
            }
        }
        return null; // product has not been found
    }
    // TODO: see if can call getProduct here
    public void removeProduct(int productID) {
        for (int i = 0; i < count; i++) {
            if (products[i].getId() == productID) {
                products[i] = products[count - 1]; // move last product to the current position
                products[count - 1] = null; // deletes the product
                count--; // decreases the inventory count
                return;
            }
        }
    }

    public void listProducts() {
        for (int i = 0; i < count; i++) {
            System.out.println(products[i]);
        }
    }

}
