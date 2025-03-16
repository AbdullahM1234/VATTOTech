import java.util.ArrayList;
import java.util.Scanner;
class Product {
    String name;
    int quantity;
    double price;
    String sku;

    Product(String name, int quantity, double price, String sku) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.sku = sku;

    }

    @Override
    public String toString() {
        return name + " | SKU:"+ sku + " | Quantity: " + quantity + " | Price: $" + price;
    }

    public String toFile() {
        return name + "," + quantity + "," + price + "," + sku;
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
            System.out.println("Choose an option:");
            System.out.println("1. Add Product");
            System.out.println("2. Add Products from File");
            System.out.println("3. Remove Product");
            System.out.println("4. Search Inventory");
            System.out.println("5. View Inventory");
            System.out.println("6. Sort Inventory");
            System.out.println("7. Edit Inventory");
            System.out.println("8. Exit");


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
                    sortInventory();
                    break;
                case 7:
                    editProduct();
                    break;
                case 8:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
