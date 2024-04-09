public interface Menu {
    void displayMenu();
    Menu handleUserInput(); // returns next menu or null for exit
}
