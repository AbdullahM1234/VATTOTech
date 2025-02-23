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
        return " | SKU:"+ sku+ name + " | Quantity: " + quantity + " | Price: $" + price;
    }
}

public class InventoryManager {
    private static final ArrayList<Product> inventory = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("Choose an option: \n");
            System.out.println("1. Add Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Remove Product");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            }
        }
    }

