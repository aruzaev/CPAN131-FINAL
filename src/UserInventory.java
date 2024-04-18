import java.util.ArrayList;

public class UserInventory {
    private ArrayList<User> users;
    private final UserCSV userCSV;

    public UserInventory() {
        this.userCSV = new UserCSV();
        this.users = userCSV.loadUsersFromCSV();
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            userCSV.saveUsersToCSV(users);
        } else {
            System.out.println("User already exists.");
        }
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean removeUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username)) {
                if (i == 0) {
                    System.out.println("Cannot delete the first user.");
                    return false; // Cannot remove the first user
                }
                users.remove(i);
                userCSV.saveUsersToCSV(users);
                return true; // User successfully removed
            }
        }
        System.out.println("User not found.");
        return false; // User not found or could not be removed
    }


    public void listUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void updateUser(User updatedUser) {
        for (User user : users) {
            if (user.getUsername().equals(updatedUser.getUsername())) {
                user.setPassword(updatedUser.getPassword());
                user.setAdmin(updatedUser.isAdmin());
                userCSV.saveUsersToCSV(users);
                System.out.println("User updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }
}
