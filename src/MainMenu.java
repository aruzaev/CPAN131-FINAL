public class MainMenu implements Menu{

    @Override
    public void displayMenu() {
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.println("0. Exit");
        handleUserInput();

    }

    @Override
    public void handleUserInput() {

    }
}
