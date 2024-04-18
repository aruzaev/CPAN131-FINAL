public class Main {
    public static void main(String[] args) {
// Initialize Inventory (which loads products from CSV)
        Inventory stock = new Inventory();


        Menu mainMenu = new MainMenu(stock); // initialized a new main menu
        while (mainMenu != null) {
            mainMenu.displayMenu();
            mainMenu = mainMenu.handleUserInput();
        }

        System.out.println("Goodbye!!");
    }
}