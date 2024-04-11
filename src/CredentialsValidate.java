import java.util.ArrayList;

public class CredentialsValidate {
    private static final UserCSV userCSV = new UserCSV();

    public static boolean validateUser(String username, String password) {
        ArrayList<User> users = userCSV.loadUsersFromCSV();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean adminCheck(String username, String password) {
        ArrayList<User> users = userCSV.loadUsersFromCSV();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.isAdmin()) {
                return true;
            }
        }
        return false;
    }

    public static boolean userExists(String username) {
        ArrayList<User> users = userCSV.loadUsersFromCSV();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static void createUser(String username, String password, boolean isAdmin) {
        ArrayList<User> users = userCSV.loadUsersFromCSV();
        if (!userExists(username)) {
            users.add(new User(username, password, isAdmin));
            userCSV.saveUsersToCSV(users);
        } else {
            System.out.println("User already exists.");
        }
    }
}
