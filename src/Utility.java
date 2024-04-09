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

    public static String getUserInput() {
        System.out.println("Enter your input: ");
        // No need to loop here; nextLine() will return the next complete line of input
        String input = scanner.nextLine();
        return input;
    }


//    public static String getUserInput() {
//        while (!scanner.hasNextLine()) { // loop while the input is not a string
//            System.out.println("Invalid input, must be a string: ");
//            scanner.next();
//        }
//
//        String input = scanner.nextLine();
//        scanner.nextLine();
//        return input;
//    }

    public static double getUserDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input, must be a double: ");
            scanner.next();
        }

        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }
}
