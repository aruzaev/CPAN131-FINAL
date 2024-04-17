import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryCSV {
    private final String FILENAME = "src/CSV/Inventory.csv";

    public ArrayList<Product> loadInventoryFromCSV() {
        ArrayList<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                products.add(parseProductFromCSVLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private Product parseProductFromCSVLine(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 6) {
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String description = parts[2];
            String category = parts[3];
            int quantity = Integer.parseInt(parts[4]);
            double price = Double.parseDouble(parts[5]);
            return new Product(id, name, description, category, quantity, price);
        }
        throw new IllegalArgumentException("Invalid CSV line format: " + csvLine);
    }

    public void saveInventoryToCSV(ArrayList<Product> products) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Product product : products) {
                bw.write(product.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
