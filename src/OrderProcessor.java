// removes coupling and Receipt's dependancy on Inventory
// is responsible for processing orders

public class OrderProcessor {
    private Inventory stock;
    private Receipt receipt;

    public OrderProcessor(Inventory stock) {
        this.stock = stock;
        this.receipt = new Receipt(); // generates a new receipt
    }

    // TODO: ADD MORE ERROR CASES (if product doesnt exist an for if theres not enough quantity)
    public boolean addToPurchase(Product product, int quantity) {
        if (stock.getProduct(product.getId()) != null && // if product exists and there is enough stock
        stock.decreaseQuantity(product.getId(), quantity)) {
            receipt.addItem(product, quantity);
            System.out.println("Added " + quantity + " of " + product.getName());
            return true;
        } else {
            System.out.println("Cannot add " + quantity + " of " + product.getName());
            return false;
        }
    }

    public void finalizePurchase() {
        receipt.printReceipt();
    }

    // getter for receipt
    public Receipt getReceipt() {
        return this.receipt;
    }
}
