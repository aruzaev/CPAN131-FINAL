public class Main {
    public static void main(String[] args) {

        Inventory stock = new Inventory(10);
        // id, name, description, quantity, price
        Product product1 = new Product(1, "Apple", "Green apple", 256, 4.99);
        Product product2 = new Product(2, "Orange", "", 13, 5.99);
        Product product3 = new Product(3, "RTX 4070ti", "Food", 1, 1500.99);

        System.out.println("Adding products");

        stock.addProduct(product1);
        stock.addProduct(product2);
        stock.addProduct(product3);

        System.out.println("Listing products");
        stock.listProducts();

        //System.out.println("Removing products");
        //stock.removeProduct(2);

        stock.listProducts();

        // buying things
        Receipt receipt = new Receipt();

        receipt.addItem(product1, 3);
        receipt.addItem(product2, 5);
        receipt.addItem(product3, 1);

        receipt.printReceipt();


    }
}