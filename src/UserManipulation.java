public class UserManipulation implements Menu {
    private Inventory stock;
    private UserInventory userInventory = new UserInventory();

    public UserManipulation(Inventory stock) {
        this.stock = stock;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nUser Manipulation");
        System.out.println("===================");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Update User");
        System.out.println("4. List all Users");
        System.out.println("5. Back to main menu");
        System.out.println("6. Back to Admin menu");
        System.out.println("0. Exit");
    }

    @Override
    public Menu handleUserInput() {
        System.out.println("Enter the choice: ");
        int choice = Utility.getUserChoice();

        switch (choice) {
            case 1:
                addUser();
                break;
            case 2:
                removeUser();
                break;
            case 3:
                updateUser();
                break;
            case 4:
                listUsers();
                break;
            case 5:
                return new MainMenu(stock);
            case 6:
                return new AdminMenu(stock);
            case 0:
                System.out.println("See you soon!");
                System.exit(0);
            default:
                System.out.println("Invalid choice, try again.");
        }
        return this;
    }

    private void addUser() {
        System.out.println("\n=======Add User=======");
        System.out.print("Enter Username: ");
        String username = Utility.getUserInput().trim();

        if (CredentialsValidate.userExists(username)) {
            System.out.println("Username already exists. Please choose another.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = Utility.getUserInput().trim();

        if (!LoginValidation.isValidLogin(username, password)) {
            System.out.println("Invalid input: Username and password must not contain spaces or special characters.");
            return;
        }

        System.out.print("Is Admin? (t/T - True | f/F - False): ");
        String isAdminInput = Utility.getUserInput().trim();
        boolean isAdmin = isAdminInput.equalsIgnoreCase("t");
        if (!LoginValidation.isAdmin(isAdminInput)) {
            System.out.println("Invalid input for admin status. Please use 't' or 'f'.");
            return;
        }

        // All checks passed, add the user
        User newUser = new User(username, password, isAdmin);
        userInventory.addUser(newUser);
        System.out.println("User added successfully!");
    }

    private void removeUser() {
        System.out.println("\n=======Remove User=======");
        listUsers();
        System.out.print("Enter Username to remove: ");
        String username = Utility.getUserInput().trim();

        if (userInventory.removeUser(username)) {
            System.out.println("User removed successfully!");
        } else {
            System.out.println("Failed to remove user or user not found.");
        }
    }

    private void updateUser() {
        System.out.println("\n=======Update User=======");
        listUsers();
        System.out.print("Enter Username to update: ");
        String username = Utility.getUserInput().trim();

        User user = userInventory.getUser(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter New Password (Press Enter to keep current): ");
        String newPassword = Utility.getUserInput().trim();
        if (!newPassword.isEmpty() && !LoginValidation.isValidLogin(username, newPassword)) {
            System.out.println("Invalid new password: Must not contain spaces or special characters.");
            return;
        }

        System.out.print("Is Admin? (t/T - True | f/F - False, Press Enter to keep current): ");
        String isAdminInput = Utility.getUserInput().trim();
        boolean isAdmin = isAdminInput.isEmpty() ? user.isAdmin() : isAdminInput.equalsIgnoreCase("t");

        // Update user details
        user.setPassword(newPassword.isEmpty() ? user.getPassword() : newPassword);
        user.setAdmin(isAdmin);
        userInventory.updateUser(user);
        System.out.println("User updated successfully!");
    }

    private void listUsers() {
        System.out.println("\nList of Users:");
        userInventory.listUsers();
    }
}
