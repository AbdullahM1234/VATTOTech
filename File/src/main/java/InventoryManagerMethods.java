import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InventoryManagerMethods {
    protected static void loadInventoryFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(InventoryManager.databaseFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim());
                    String sku = parts[3].trim();

                    ArrayList<String> tags = new ArrayList<>();
                    for (int i = 4; i < parts.length; i++) {
                        tags.add(parts[i].trim());
                    }

                    InventoryManager.inventory.add(new Product(name, quantity, price, sku, tags));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory from file: " + e.getMessage());
        }
    }

    protected static void addProduct() {
        System.out.print("Enter product name: ");
        String name = InventoryManager.scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = InventoryManager.scanner.nextInt();
        System.out.print("Enter price: ");
        double price = InventoryManager.scanner.nextDouble();
        InventoryManager.scanner.nextLine();
        System.out.print("Enter SKU: ");
        String sku = InventoryManager.scanner.nextLine();

        ArrayList<String> tags = new ArrayList<>();
        System.out.println("Enter tags (comma-separated: 'Toy Type, Brand, Age and up'): ");
        String tagInput = InventoryManager.scanner.nextLine();
        if (!tagInput.isEmpty()) {
            String[] tagArray = tagInput.split(",");
            for (String tag : tagArray) {
                tags.add(tag.trim());
            }
        }

        for (Product product : InventoryManager.inventory) {
            if (product.name.equalsIgnoreCase(name) || product.sku.equalsIgnoreCase(sku)) {
                product.quantity += quantity;
                System.out.println("Existing product found. Quantity updated!");
                updateFile();
                return;
            }
        }

        InventoryManager.inventory.add(new Product(name, quantity, price, sku, tags));
        System.out.println("Product added successfully!");
        updateFile();
    }

    public static void addProductFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("inventory_sheet_1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim());
                    String sku = parts[3].trim();

                    ArrayList<String> tags = new ArrayList<>();
                    for (int i = 4; i < parts.length; i++) {
                        tags.add(parts[i].trim());
                    }

                    boolean exists = false;
                    for (Product product : InventoryManager.inventory) {
                        if (product.name.equalsIgnoreCase(name) || product.sku.equalsIgnoreCase(sku)) {
                            product.quantity += quantity;
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        InventoryManager.inventory.add(new Product(name, quantity, price, sku, tags));
                    }
                }
            }
            System.out.println("Products added successfully from file!");
            updateFile();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    protected static void viewInventory() {
        if (InventoryManager.inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product product : InventoryManager.inventory) {
                System.out.println(product);
            }
        }
    }

    protected static void removeProduct() {
        System.out.print("Enter SKU of product to remove: ");
        String sku = InventoryManager.scanner.nextLine();

        for (int i = 0; i < InventoryManager.inventory.size(); i++) {
            if (InventoryManager.inventory.get(i).sku.equals(sku)) {
                InventoryManager.inventory.remove(i);
                System.out.println("Product removed successfully!");

                InventoryManagerMethods.updateFile();
                return;
            }
        }

        System.out.println("Product not found.");
    }

    private static void updateFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(InventoryManager.databaseFilePath))) {
            for (Product product : InventoryManager.inventory) {
                bw.write(product.toFile());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    protected static void editProduct() {
        System.out.println("Current Inventory:");
        viewInventory();

        System.out.print("Enter SKU of product to edit (or type 'Cancel' to exit): ");
        String targetSKU = InventoryManager.scanner.nextLine().trim();
        if (targetSKU.equalsIgnoreCase("Cancel")) {
            return;
        }

        Product product = null;
        for (Product p : InventoryManager.inventory) {
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
            System.out.println("5. Edit Tags");
            System.out.println("6. Exit Editing");
            System.out.print("Enter your choice: ");
            int choice = InventoryManager.scanner.nextInt();
            InventoryManager.scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    product.name = InventoryManager.scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter new SKU: ");
                    product.sku = InventoryManager.scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Enter new quantity: ");
                    product.quantity = InventoryManager.scanner.nextInt();
                    InventoryManager.scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter new price: ");
                    product.price = InventoryManager.scanner.nextDouble();
                    InventoryManager.scanner.nextLine();
                    break;
                case 5:
                    System.out.println("Enter new tags (comma-separated: 'Toy Type, Brand, Age and up'): ");
                    String tagInput = InventoryManager.scanner.nextLine();
                    product.tags.clear();
                    if (!tagInput.isEmpty()) {
                        String[] tagArray = tagInput.split(",");
                        for (String tag : tagArray) {
                            product.tags.add(tag.trim());
                        }
                    }
                    break;
                case 6:
                    editing = false;
                    continue;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            updateFile();
        }
    }

    protected static void searchInventory() {
        System.out.println("Search by:");
        System.out.println("1. Product Name");
        System.out.println("2. SKU");
        int searchChoice = InventoryManager.scanner.nextInt();
        InventoryManager.scanner.nextLine();

        if (searchChoice == 1) {
            System.out.print("Enter product name: ");
            String name = InventoryManager.scanner.nextLine();
            boolean found = false;

            for (Product product : InventoryManager.inventory) {
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
            String sku = InventoryManager.scanner.nextLine();
            boolean found = false;

            for (Product product : InventoryManager.inventory) {
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

    protected static void sortInventory() {
        System.out.println("Sort by:");
        System.out.println("1. Name (Alphabetically)");
        System.out.println("2. SKU (Increasing)");
        int sortChoice = InventoryManager.scanner.nextInt();
        InventoryManager.scanner.nextLine(); // consume the newline character

        switch (sortChoice) {
            case 1:
                Collections.sort(InventoryManager.inventory, new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.name.compareToIgnoreCase(p2.name);
                    }
                });
                System.out.println("Inventory sorted by name.");
                break;
            case 2:
                Collections.sort(InventoryManager.inventory, new Comparator<Product>() {
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

        InventoryManagerMethods.updateFile();
        InventoryManagerMethods.viewInventory();
    }

    protected static void inventoryOverview() {
        final int lowStockThreshold = 5;

        int lowStockCount = 0;
        int zeroStockCount = 0;

        System.out.println("\n==== Inventory Overview ====");
        System.out.println("Stock Status Report:");

        for (Product product : InventoryManager.inventory) {
            if (product.quantity == 0) {
                zeroStockCount++;
                System.out.println("[OUT OF STOCK] " + product.name + " (SKU: " + product.sku + ")");
            } else if (product.quantity < lowStockThreshold) {
                lowStockCount++;
                System.out.println("[LOW STOCK] " + product.name + " (SKU: " + product.sku + ") - " + product.quantity + " left");
            }
        }

        System.out.println("\nSummary:");
        System.out.println("Total items below threshold (" + lowStockThreshold + "): " + lowStockCount);
        System.out.println("Items at zero stock: " + zeroStockCount);

        if (lowStockCount == 0 && zeroStockCount == 0) {
            System.out.println("All items are sufficiently stocked.");
        }
    }

    protected static boolean login() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter username: ");
            String UsernameInput = InventoryManager.scanner.nextLine();
            System.out.print("Enter password: ");
            String PasswordInput = InventoryManager.scanner.nextLine();

            if (UsernameInput.equals(InventoryManager.Username) && PasswordInput.equals(InventoryManager.Password)) {
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
