import java.util.ArrayList;

public class CredentialsValidate {
    // Static reference to UserCSV to handle CSV operations
    private static final UserCSV userCSV = new UserCSV();

    // Validate user credentials against the data in the CSV file
    public static boolean validateUser(String username, String password) {
        ArrayList<User> users = userCSV.loadUsersFromCSV(); // Load users from CSV
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // Return true if both username and password match
            }
        }
        return false; // Return false if no match is found
    }

    // Check if the user has admin privileges
    public static boolean adminCheck(String username, String password) {
        ArrayList<User> users = userCSV.loadUsersFromCSV(); // Load users from CSV
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.isAdmin()) {
                return true; // Return true if user is an admin
            }
        }
        return false; // Return false if no admin user matches
    }

    // Check if a username already exists in the system
    public static boolean userExists(String username) {
        ArrayList<User> users = userCSV.loadUsersFromCSV(); // Load users from CSV
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true; // Return true if username is found
            }
        }
        return false; // Return false if username is not found
    }

    // Create a new user and save to CSV if the username does not already exist
    public static void createUser(String username, String password, boolean isAdmin) {
        ArrayList<User> users = userCSV.loadUsersFromCSV(); // Load current users
        if (!userExists(username)) { // Check if username already exists
            users.add(new User(username, password, isAdmin)); // Add new user
            userCSV.saveUsersToCSV(users); // Save updated user list to CSV
        } else {
            System.out.println("User already exists."); // Print error if user exists
        }
    }
}
