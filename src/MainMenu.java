public class MainMenu implements Menu{

    private Inventory stock;

    public MainMenu(Inventory stock) {
        this.stock = stock;
    }

    @Override
    public void displayMenu() {
        System.out.println("\n1. Admin Page");
        System.out.println("2. Customer Page");
        System.out.println("3. Logout");
        System.out.println("0. Exit");
    }

    @Override
    public Menu handleUserInput() {
        System.out.println("Enter choice: ");
        int choice = Utility.getUserChoice();
        switch (choice) {
            case 1:
            case 2:
                if (Utility.isLoggedIn) {
                    if (choice == 1 && Utility.isAdmin){
                        System.out.println("Directing to Admin Menu");
                        return new AdminMenu(stock);
                    }else if (choice == 1 && !Utility.isAdmin){
                        System.out.println("You Really thought did ya?");
                        System.out.print("Redirecting you to the correct page :)\n");
                        return new CustomerMenu(stock);
                    }else{
                        System.out.println("Directing to Customer Menu");
                        return new CustomerMenu(stock);
                    }
                }else{
                    return new LogInManager(stock, choice);
                }
            case 3:
                Utility.isLoggedIn = false;
                Utility.isAdmin = false;
                System.out.print("You Have successfully logged out\n");
                return this;
            case 0:
                System.out.println("See you soon!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Try again");
                return this; // stay in the current menu
        }
    }
}
