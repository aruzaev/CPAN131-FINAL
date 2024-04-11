// This class represents a login manager for a system. It implements the Menu interface.
public class LogInManager implements Menu {
    // The inventory of the system.
    private Inventory stock;
    // The user's choice from the menu.
    private int choice;

    // A static instance of LoginValidation for validating login credentials.
    private static LoginValidation loginVal = new LoginValidation();

    // Constructor for the LogInManager class.
    public LogInManager(Inventory stock, int choice) {
        this.stock = stock;
        this.choice = choice;
    }

    // Displays the login menu.
    @Override
    public void displayMenu() {
        System.out.println("\nPlease Identify Yourself First");
        System.out.println("==================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Back to main menu");
    }

    // Handles the user's input for the login menu.
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

    // Handles the login process.
    private Menu login() {
        System.out.println("\n=======Login=======");
        System.out.print("UserName - ");
        String username = Utility.getUserInput();
        System.out.print("Password - ");
        String password = Utility.getUserInput();

        // Validates the login credentials.
        if (!loginVal.isValidLogin(username, password)) {
            System.out.println("Please check Your Username or Password for any spaces");
            return login(); // Return this menu to allow the user to try again
        } else if (CredentialsValidate.validateUser(username, password)) {
            Utility.isLoggedIn = true;
            Utility.username = username;
            System.out.println("Logged in as: " + Utility.username); // Add this line to log the username

            // Checks if the user is an admin.
            if (choice == 1 && CredentialsValidate.adminCheck(username, password)) {
                Utility.isAdmin = true;
                System.out.println("Accessing Admin Panel...");
                return new AdminMenu(stock); // Move to the AdminMenu
            } else if (choice == 2 && CredentialsValidate.adminCheck(username, password)) {
                Utility.isAdmin = true;
                System.out.println("Accessing Customer Menu...");
                return new CustomerMenu(stock); // move to the customer
            }else{
                Utility.isAdmin = false;
                System.out.println("Accessing Customer Menu...");
                return new CustomerMenu(stock); // move to the customer menu if not admin
            }
        } else {
            System.out.println("Invalid credentials. No User found.");
            return this; // Return this menu to allow the user to try again
        }
    }

    // Handles the registration process.
    private Menu register() {
        System.out.println("\n=======Register=======");
        System.out.print("Enter Username: ");
        String username = Utility.getUserInput();

        // Checks if the username already exists.
        if (CredentialsValidate.isUserExists(username)) {
            System.out.println("Username already exists. Please choose another.");
            return this; // Return this menu to allow the user to try again
        }

        System.out.print("Enter Password: ");

        String password = Utility.getUserInput();
        // Validates the login credentials.
        if (!loginVal.isValidLogin(username, password)) {
            System.out.println("Please check Your Username or Password for any spaces");
            return this; // stay at the current menu
        }

        // Writes the user's credentials to the system with the assumption that it is not an admin
        CredentialsValidate.writeUser(username, password, false);
        System.out.println("User registered successfully!");
        return this; // Return this menu to allow the user to log in
    }
}