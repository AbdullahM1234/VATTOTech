import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private static final String databaseFilePath = "toys_and_games_database.txt";

    private static final String Username = "StoreManager123";
    private static final String Password = "A1B2C3D4";

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

    private static void loadInventoryFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(databaseFilePath))) {
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
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory from file: " + e.getMessage());
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

        for (Product product : inventory) {
            if (product.name.equalsIgnoreCase(name) || product.sku.equalsIgnoreCase(sku)) {
                product.quantity += quantity;
                System.out.println("Existing product found. Quantity updated!");
                updateFile();
                return;
            }
        }

        inventory.add(new Product(name, quantity, price, sku));
        System.out.println("Product added successfully!");

        updateFile();
    }

    public static void addProductFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("inventory_sheet_1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0].trim();
                    System.out.println(name);
                    int quantity = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim());
                    String sku = parts[3].trim();

                    boolean exists = false;
                    for (Product product : inventory) {
                        if (product.name.equalsIgnoreCase(name) || product.sku.equalsIgnoreCase(sku)) {
                            product.quantity += quantity;
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        inventory.add(new Product(name, quantity, price, sku));
                    }
                }
            }
            System.out.println("Products added successfully from file!");
            updateFile();
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(databaseFilePath))) {
            for (Product product : inventory) {
                bw.write(product.toFile());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void editProduct() {
        System.out.println("Current Inventory:");
        viewInventory();

        System.out.print("Enter SKU of product to edit (or type 'Cancel' to exit): ");
        String targetSKU = scanner.nextLine().trim();
        if (targetSKU.equalsIgnoreCase("Cancel")) {
            return;
        }

        Product product = null;
        for (Product p : inventory) {
            if (p.sku.equalsIgnoreCase(targetSKU)) {
                product = p;
                break;
            }
        }
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("\nEditing product: " + product);
            System.out.println("1. Edit Name");
            System.out.println("2. Edit SKU");
            System.out.println("3. Edit Quantity");
            System.out.println("4. Edit Price");
            System.out.println("5. Exit Editing");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    product.name = scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter new SKU: ");
                    product.sku = scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Enter new quantity: ");
                    product.quantity = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter new price: ");
                    product.price = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                case 5:
                    editing = false;
                    continue;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

            updateFile();
        }
    }

    private static void searchInventory() {
        System.out.println("Search by:");
        System.out.println("1. Product Name");
        System.out.println("2. SKU");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

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

    private static void sortInventory() {
        System.out.println("Sort by:");
        System.out.println("1. Name (Alphabetically)");
        System.out.println("2. SKU (Increasing)");
        int sortChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        switch (sortChoice) {
            case 1:
                Collections.sort(inventory, new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.name.compareToIgnoreCase(p2.name);
                    }
                });
                System.out.println("Inventory sorted by name.");
                break;
            case 2:
                Collections.sort(inventory, new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.sku.compareTo(p2.sku);
                    }
                });
                System.out.println("Inventory sorted by SKU.");
                break;
            default:
                System.out.println("Invalid choice.");
        }

        updateFile();
        viewInventory();
    }

    private static boolean login() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter username: ");
            String UsernameInput = scanner.nextLine();
            System.out.print("Enter password: ");
            String PasswordInput = scanner.nextLine();

            if (UsernameInput.equals(Username) && PasswordInput.equals(Password)) {
                System.out.println("Login successful!\n");
                return true;
            } else {
                attempts--;
                System.out.println("WRONG!. Attempts remaining: " + attempts);
            }
        }
        return false;
    }
}
