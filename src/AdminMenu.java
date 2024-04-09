public class AdminMenu implements Menu{

    @Override
    public void displayMenu() {
        System.out.println("1. Add product");
        System.out.println("2. Remove product");
        System.out.println("3. Edit description");
        System.out.println("4. Edit price");
        System.out.println("5. Edit quantity");
        System.out.println("0. Exit");
    }

    @Override
    public Menu handleUserInput() {

        return null;
    }
}
