import java.util.Scanner;

public class Utility {
    // makes the scanner have global presence and ensures that itt cannot be overwritten by another class (one instance of scanner for the entire app)
    private static final Scanner scanner = new Scanner(System.in);

    public static int getUserChoice() {
        System.out.println("Enter your choice: ");
        while (!scanner.hasNextInt()) { // loop while the input is not a number
            System.out.println("Invalid choice, must be a number.");
            scanner.next(); // discard input
            System.out.println("Enter your choice: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}
