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
        if (users.size() > 1) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(username)) {
                    if (i == 0) {
                        System.out.println("Cannot delete the first user.");
                        return;
                    }
                    users.remove(i);
                    userCSV.saveUsersToCSV(users);
                    return;
                }
            }
        } else {
            System.out.println("Cannot delete the only user.");
        }
        System.out.println("User not found.");
    }


    public void listUsers() {
        for (int i = 1; i < users.size(); i++) {
            System.out.println(users.get(i));
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
