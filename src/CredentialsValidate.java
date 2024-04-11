import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialsValidate {
    private static final String FILENAME = "src/CSV/UserData.csv";

    public static List<String[]> readUsers() {
        List<String[]> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                users.add(userData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void writeUser(String username, String password, boolean isAdmin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {
            bw.write(username + "," + password + "," + isAdmin);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isUserExists(String username) {
        List<String[]> users = readUsers();
        for (String[] userData : users) {
            if (userData[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateUser(String username, String password) {
        List<String[]> users = readUsers();
        for (String[] userData : users) {
            if (userData[0].equals(username) && userData[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean adminCheck(String username, String password) {
        List<String[]> users = readUsers();
        for (String[] userData : users) {
            if (userData[0].equals(username) && userData[1].equals(password) && Boolean.parseBoolean(userData[2])) {
                return true; // User found and is an admin
            }
        }
        return false; // User not found, incorrect credentials, or not an admin
    }

}