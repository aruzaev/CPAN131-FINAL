public class MainMenu implements Menu{

    private Inventory stock;

    public MainMenu(Inventory stock) {
        this.stock = stock;
    }

    @Override
    public void displayMenu() {
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.println("0. Exit");
    }

    @Override
    public Menu handleUserInput() {
        System.out.println("Enter choice: ");
        int choice = Utility.getUserChoice();
        switch (choice) {
            case 1:
                return new AdminMenu(stock);
            case 2:
                return new CustomerMenu(stock);
            case 0:
                System.out.println("See you soon!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Try again");
                return this; // stay in the current menu
        }
    }
}
