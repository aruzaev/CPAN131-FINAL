public class CustomerMenu implements Menu{
    private Inventory stock;

    public CustomerMenu(Inventory stock) {
        this.stock = stock;
    }

    @Override
    public void displayMenu() {
        System.out.println("1. View products");
        System.out.println("2. View cart");
    }

    @Override
    public Menu handleUserInput() {
        return null;
    }
}
