import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormattedGroceries {
    static class GroceryItem {
        int id;
        String item;
        double quantity;
        double price;

        public GroceryItem(int id, String item, double quantity, double price) {
            this.id = id;
            this.item = item;
            this.quantity = quantity;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        List<GroceryItem> items = new ArrayList<>();
        double total = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Groceries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String item = parts[1];
                    double quantity = Double.parseDouble(parts[2].replace("KG", ""));
                    double price = Double.parseDouble(parts[3]);
                    items.add(new GroceryItem(id, item, quantity, price));
                    total += quantity * price;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Groceries.txt: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FormattedGroceries.txt"))) {
            writer.write("| ID | Item            | Quantity(KG) | Price(€) |");
            writer.newLine();
            writer.write("|----|-----------------|--------------|----------|");
            writer.newLine();

            for (GroceryItem item : items) {
                writer.write(String.format("| %2d | %-15s | %12.1f | %8.2f |",
                        item.id, item.item, item.quantity, item.price));
                writer.newLine();
            }

            writer.write("*************************************************************");
            writer.newLine();
            writer.write(String.format("The grocery shopping total is: €%.2f", total));
            writer.newLine();
            writer.write("*************************************************************");
        } catch (IOException e) {
            System.out.println("Error writing FormattedGroceries.txt: " + e.getMessage());
        }
    }
}    