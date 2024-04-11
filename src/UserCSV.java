import java.io.*;
import java.util.ArrayList;

public class UserCSV {
    private static final String FILENAME = "src/CSV/UserData.csv";

    public ArrayList<User> loadUsersFromCSV() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(parseUserFromCSVLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User parseUserFromCSVLine(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 3) {
            String username = parts[0];
            String password = parts[1];
            boolean isAdmin = Boolean.parseBoolean(parts[2]);
            return new User(username, password, isAdmin);
        }
        throw new IllegalArgumentException("Invalid CSV line format: " + csvLine);
    }

    public void saveUsersToCSV(ArrayList<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (User user : users) {
                bw.write(user.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
