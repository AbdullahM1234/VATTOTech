import java.util.ArrayList;
import java.util.Scanner;

class Product {
    String name;
    int quantity;
    double price;
    String sku;
    ArrayList<String> tags;

    Product(String name, int quantity, double price, String sku, ArrayList<String> tags) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.sku = sku;
        this.tags = tags != null ? tags : new ArrayList<>();
    }

    @Override
    public String toString() {
        return name + " | SKU: " + sku + " | Quantity: " + quantity + " | Price: $" + price + " | Tags: " + String.join(", ", tags);
    }

    public String toFile() {
        return name + "," + quantity + "," + price + "," + sku + "," + String.join(",", tags);
    }
}

public class InventoryManager extends InventoryManagerMethods {
    public static ArrayList<Product> inventory = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static String databaseFilePath = "toys_and_games_database.txt";

    static final String Username = "StoreManager123";
    static final String Password = "A1B2C3D4";

    public static void main(String[] args) {
        if (!login()) {
            System.out.println("Too many failed attempts. Exiting program...");
            return;
        }
        loadInventoryFromFile();

        while (true) {
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1. Add Product");
            System.out.println("2. Add Products from File");
            System.out.println("3. Remove Product");
            System.out.println("4. Search Inventory");
            System.out.println("5. View Inventory");
            System.out.println("6. Inventory Overview");
            System.out.println("7. Sort Inventory");
            System.out.println("8. Edit Inventory");
            System.out.println("9. Generate Report");
            System.out.println("10. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    addProductFromFile();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    searchInventory();
                    break;
                case 5:
                    viewInventory();
                    break;
                case 6:
                    inventoryOverview();
                    break;
                case 7:
                    sortInventory();
                    break;
                case 8:
                    editProduct();
                    break;
                case 9:
                    generateInventoryReport();
                case 10:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
