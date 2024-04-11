public class LogInManager implements Menu {
    private Inventory stock;
    private int choice;

    private boolean isAdmin;

    private static LoginValidation loginVal = new LoginValidation();

    public LogInManager(Inventory stock, int choice) {
        this.stock = stock;
        this.choice = choice;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nPlease Identify yourself");
        System.out.println("=============================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Back to main menu");
    }

    @Override
    public Menu handleUserInput() {
        System.out.println("Enter choice: ");
        switch (Utility.getUserChoice()) {
            case 1:
                return login();
            case 2:
                return register();
            case 0:
                return new MainMenu(stock);
            default:
                System.out.println("Invalid choice. Try again.");
        }
        return this;
    }

    private Menu login() {
        System.out.println("\n=======Login=======");
        System.out.print("UserName - ");
        String username = Utility.getUserInput();
        System.out.print("Password - ");
        String password = Utility.getUserInput();

        if (!loginVal.isValidLogin(username, password)) {
            System.out.println("Please check Your Username or Password for any spaces");
            return login(); // Return this menu to allow the user to try again
        } else if (CredentialsValidate.validateUser(username, password)) {
            Utility.isLoggedIn = true;
            Utility.username = username;

            if (choice == 1 && CredentialsValidate.adminCheck(username, password)) {
                isAdmin = true;
                return new AdminMenu(stock); // Move to the AdminMenu
            } else if (choice == 1 && !CredentialsValidate.adminCheck(username, password)) {
                isAdmin = false;
                System.out.println("You have tried to access the Admin Panel without having proper privileges");
            }
            return new CustomerMenu(stock); // Move to the CustomerMenu
        } else {
            System.out.println("No User Found");
            return this; // Return this menu to allow the user to try again
        }
    }

    private Menu register() {
        System.out.println("\n=======Register=======");
        System.out.print("Enter UserName: ");
        String username = Utility.getUserInput();


        if (CredentialsValidate.isUserExists(username)) {
            System.out.println("Username already exists. Please choose another.");
            return register(); // Return this menu to allow the user to try again
        }

        System.out.print("Enter Password: ");
        
        String password = Utility.getUserInput();
        if (!loginVal.isValidLogin(username, password)) {
            System.out.println("Please check Your Username or Password for any spaces");
            return register();
        }

        boolean isAdmin = false;

        CredentialsValidate.writeUser(username, password, isAdmin);
        System.out.println("User registered successfully!");
        return this; // Return this menu to allow the user to log in
    }

}
