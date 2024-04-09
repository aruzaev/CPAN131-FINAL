public class CustomerMenu implements Menu{
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
