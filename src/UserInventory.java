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

    public void removeUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                userCSV.saveUsersToCSV(users);
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void listUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void updateUser(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                userCSV.saveUsersToCSV(users);
                System.out.println("User updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }
}
