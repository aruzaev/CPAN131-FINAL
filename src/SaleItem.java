//this class helps to manage a single item in a transaction, which helps to manage many items that are in any given transaction
public class SaleItem {
    private Product product;
    private int quantity;
    private double totalPrice;

    public SaleItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity; // price * quantity
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return product.getName() + " x " + quantity + " = $" + totalPrice;
    }
}
