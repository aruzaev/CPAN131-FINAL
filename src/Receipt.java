// TODO: Add colors to the receipts
public class Receipt {
    private SaleItem[] items; // using composition to create an array of saleitem objectss
    private int itemCount;
    private double total;

    public Receipt() {
        this.items = new SaleItem[10]; // can start with any size because this is just the initial size
        this.itemCount = 0;
        this.total = 0.0;
    }

    private void checkCapacity() {
        if (itemCount >= items.length) { // if the number of items currently in the receipt is greater than the size of the array
            SaleItem[] newItems = new SaleItem[items.length * 2]; // create a new array with double the size of the old one
            // copying items from old array into the new one
            for (int i = 0; i < items.length; i++) {
                newItems[i] = items[i];
            }

            items = newItems; // old array referencing the new and larger array
        }
    }
    // TODO: Add checks to see if the quantity is valid (not too big and not too small)
    public void addItem(Product product, int quantity) {
        checkCapacity(); // check if the array needs to be resized
        SaleItem newItem = new SaleItem(product, quantity);
        items[itemCount] = newItem; // put newItem into the next available index
        itemCount++;

        total += newItem.getTotalPrice(); // updates total price

    }

    public void printReceipt() {
        System.out.println("RECEIPT: ");
        for (int i = 0; i < itemCount; i++) {
            System.out.println(items[i]);
        }
        System.out.println("Total: $" + total);
    }
}
