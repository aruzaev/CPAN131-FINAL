public class UserManipulation implements Menu {
    private Inventory stock;
    UserInventory userInventory = new UserInventory();

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
                return new MainMenu(stock); // go back to main menu

            case 6:
                return new AdminMenu(stock); // go back to Admin Menu
            case 0:
                System.out.println("See you soon!");
                System.exit(0);
            default:
                System.out.println("Invalid choice, try again.");
        }
        return this; // stay in the admin menu
    }

    private void addUser() {
        System.out.println("\n=======Add User=======");
        System.out.print("Enter Username: ");
        String username = Utility.getUserInput();

        if (CredentialsValidate.userExists(username)) {
            System.out.println("Username already exists. Please choose another.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = Utility.getUserInput();

        if(!LoginValidation.isValidLogin(username, password)){
            System.out.println("Make sure That there are no spaces in the credentials.");
            return;
        }

        System.out.print("Is Admin? (t/T - True | f/F - False): ");
        String isAdmin = Utility.getUserInput();

        if (!LoginValidation.isAdmin(isAdmin)){
            System.out.println("Please make sure that \"Is Admin\" follows the proper input format");
            return;
        }else{
            if (isAdmin.equals("t") || isAdmin.equals("T")){
                isAdmin = "true";
            }else if (isAdmin.equals("f") || isAdmin.equals("F")){
                isAdmin = "ok";
            }

            CredentialsValidate.createUser(username, password, Boolean.parseBoolean(isAdmin));
        }
        System.out.println("User added successfully!");
    }


    private void removeUser() {
        System.out.println("\n=======Remove User=======");
        listUsers();
        System.out.print("Enter Username to remove: ");
        String username = Utility.getUserInput();

        UserInventory userInventory = new UserInventory();
        userInventory.removeUser(username);
    }

    private void updateUser() {
        System.out.println("\n=======Update User=======");
        listUsers();
        System.out.print("Enter Username to update: ");
        String username = Utility.getUserInput();

        UserInventory userInventory = new UserInventory();
        User user = userInventory.getUser(username);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter New Password (Press Enter to keep current): ");
        String newPassword = Utility.getUserInput();

        if (newPassword.isEmpty()) {
            newPassword = user.getPassword();
        } else if (!LoginValidation.isValidLogin(username, newPassword)) {
            System.out.println("Make sure that there are no spaces in the credentials.");
            return;
        }

        System.out.print("Is Admin? (t/T - True | f/F - False, Press Enter to keep current): ");
        String isAdminInput = Utility.getUserInput();

        boolean isAdmin = user.isAdmin();
        if (!isAdminInput.isEmpty()) {
            if (!LoginValidation.isAdmin(isAdminInput)){
                System.out.println("Please make sure that \"Is Admin\" follows the proper input format");
                return;
            }
            isAdmin = Boolean.parseBoolean(isAdminInput);
        }

        userInventory.removeUser(username);
        user.setPassword(newPassword);
        user.setAdmin(isAdmin);
        userInventory.addUser(user);
        System.out.println("User updated successfully!");
    }


    private void listUsers() {
        userInventory.listUsers();
    }
}
