public class Main {
    public static void main(String[] args) {

        Inventory stock = new Inventory();
        // id, name, description, quantity, price
        Product product1 = new Product(1, "Apple", "Green apple","Grocery", 256, 4.99);
        Product product2 = new Product(2, "Orange", "","Grocery", 13, 5.99);
        Product product3 = new Product(3, "RTX 4070ti", "Food","Electronics", 1, 1500.99);

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

        OrderProcessor orderProcessor = new OrderProcessor(stock);


        orderProcessor.addToPurchase(product1, 3);
        orderProcessor.addToPurchase(product2, 3);

        receipt.printReceipt();

        Menu mainMenu = new MainMenu(stock); // initialized a new main menu
        while (mainMenu != null) {
            mainMenu.displayMenu();
            mainMenu = mainMenu.handleUserInput();
        }

        System.out.println("Goodbye!!");
    }
}