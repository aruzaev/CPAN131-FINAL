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

    private Menu login() {
        System.out.println("\n=======Login=======");
        System.out.print("UserName - ");
        String username = Utility.getUserInput();
        System.out.print("Password - ");
        String password = Utility.getUserInput();
        if (LoginValidation.isValidLogin(username,password)) {
            if (CredentialsValidate.validateUser(username, password)) {
                Utility.isLoggedIn = true;
                Utility.username = username;
                System.out.println("Logged in as: " + Utility.username);

                if (choice == 1 && CredentialsValidate.adminCheck(username, password)) {
                    Utility.isAdmin = true;
                    System.out.println("Accessing Admin Panel...");
                    return new AdminMenu(stock);
                } else if (choice == 2 && CredentialsValidate.adminCheck(username, password)) {
                    Utility.isAdmin = true;
                    System.out.println("Accessing Customer Menu...");
                    return new CustomerMenu(stock);
                }
                    else {
                    Utility.isAdmin = false;
                    System.out.println("Accessing Customer Menu...");
                    return new CustomerMenu(stock);
                }
            } else {
                System.out.println("Invalid credentials. No User found.");
                return this;
            }
        } else {
            return this;
        }
    }


    private Menu register() {
        System.out.println("\n=======Register=======");
        System.out.print("Enter Username: ");
        String username = Utility.getUserInput();

        if (CredentialsValidate.userExists(username)) {
            System.out.println("Username already exists. Please choose another.");
            return this;
        }

        System.out.print("Enter Password: ");
        String password = Utility.getUserInput();

        if(LoginValidation.isValidLogin(username, password)){
            System.out.println("Make sure That there are no spaces in the credentials.");
            return this;
        }

        CredentialsValidate.createUser(username, password, false);
        System.out.println("User registered successfully!");
        return this;
    }
}
