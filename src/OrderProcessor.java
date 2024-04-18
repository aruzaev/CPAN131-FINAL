public class OrderProcessor {
    private Inventory stock;
    private Receipt receipt;

    public OrderProcessor(Inventory stock) {
        this.stock = stock;
        this.receipt = new Receipt(); // generates a new receipt
    }

    public boolean addToPurchase(int productID, int quantity) {
        Product product = stock.getProduct(productID);

        if (product != null) {
            // Check if there's enough of the product in stock
            if (stock.decreaseQuantity(productID, quantity)) {
                receipt.addItem(product, quantity);
                System.out.println("Added " + quantity + " of " + product.getName());
                return true;
            } else {
                System.out.println("Insufficient stock for " + product.getName());
                return false;
            }
        } else {
            System.out.println("Product with ID " + productID + " not found.");
            return false;
        }
    }

    public void finalizePurchase() {
        receipt.printReceipt();
        receipt.clear();
    }

    // getter for receipt
    public Receipt getReceipt() {
        return this.receipt;
    }
}
