public class LogInManager implements Menu {
    private Inventory stock;
    private int choice;

    public LogInManager(Inventory stock, int choice) {
        this.stock = stock;
        this.choice = choice;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nPlease Identify Yourself First");
        System.out.println("==================================");
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

    private Menu register() {
        System.out.println("\n=======Register=======");
        System.out.print("Enter Username: ");
        String username = Utility.getUserInput().trim();
        System.out.print("Enter Password: ");
        String password = Utility.getUserInput().trim();

        if (LoginValidation.isValidLogin(username, password)) {
            if (!CredentialsValidate.userExists(username)) {
                CredentialsValidate.createUser(username, password, false);
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Username already exists. Please choose another.");
            }
        } else {
            System.out.println("Invalid input: Username and password must not contain spaces or special characters.");
        }
        return this;
    }

    private Menu login() {
        System.out.println("\n=======Login=======");
        System.out.print("UserName - ");
        String username = Utility.getUserInput().trim();
        System.out.print("Password - ");
        String password = Utility.getUserInput().trim();

        if (LoginValidation.isValidLogin(username, password)) {
            if (CredentialsValidate.validateUser(username, password)) {
                Utility.isLoggedIn = true;
                Utility.username = username;
                System.out.println("Logged in as: " + username);
                return redirectToMenuBasedOnRole(username, password);
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        } else {
            System.out.println("Invalid input: Username and password must not contain spaces or special characters.");
        }
        return this;
    }

    private Menu redirectToMenuBasedOnRole(String username, String password) {
        if (CredentialsValidate.adminCheck(username, password)) {
            Utility.isAdmin = true;
            System.out.println("Accessing Admin Panel...");
            return new AdminMenu(stock);
        } else {
            System.out.println("Accessing Customer Menu...");
            return new CustomerMenu(stock);
        }
    }
}
