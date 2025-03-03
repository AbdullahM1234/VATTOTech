import java.io.*;
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

public class InventoryManager {
    private static final ArrayList<Product> inventory = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Product");
            System.out.println("2. Add Products from File");
            System.out.println("3. Remove Product");
            System.out.println("4. Search Inventory");
            System.out.println("5. View Inventory");
            System.out.println("6. Exit");


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
                case 6:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter SKU: ");
        String sku = scanner.nextLine();

        inventory.add(new Product(name, quantity, price, sku));
        System.out.println("Product added successfully!");

        updateFile();
    }

    public static void addProductFromFile() {
        String filePath = "toys_and_games.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim());
                    String sku = parts[3].trim();

                    inventory.add(new Product(name, quantity, price, sku));
                }
            }
            System.out.println("Products added successfully from file!");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product product : inventory) {
                System.out.println(product);
            }
        }
    }

    private static void removeProduct() {
        System.out.print("Enter SKU of product to remove: ");
        String sku = scanner.nextLine();

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).sku.equals(sku)) {
                inventory.remove(i);
                System.out.println("Product removed successfully!");

                updateFile();
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void updateFile() {
        String filePath = "toys_and_games.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : inventory) {
                bw.write(product.toFile());
                bw.newLine();
            }
            System.out.println("File updated successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void searchInventory() {
        System.out.println("Search by:");
        System.out.println("1. Product Name");
        System.out.println("2. SKU");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (searchChoice == 1) {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            boolean found = false;

            for (Product product : inventory) {
                if (product.name.equalsIgnoreCase(name)) {
                    System.out.println(product);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No product found with that name.");
            }
        } else if (searchChoice == 2) {
            System.out.print("Enter SKU: ");
            String sku = scanner.nextLine();
            boolean found = false;

            for (Product product : inventory) {
                if (product.sku.equalsIgnoreCase(sku)) {
                    System.out.println(product);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No product found with that SKU.");
            }
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }
}
