import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InventoryManagerMethodsTest {
    private Product product1, product2, product3;

    @BeforeEach
    void setUp() {
        // Initialize test inventory and scanner
        InventoryManager.inventory = new ArrayList<>();
        InventoryManager.databaseFilePath = "test_inventory.txt";

        product1 = new Product("Monopoly", 10, 5.99, "1125");
        product2 = new Product("Jenga", 2, 19.99, "1124");
        product3 = new Product("Snake", 2, 20.99, "2424");

        InventoryManager.inventory.add(product1);
        InventoryManager.inventory.add(product2);
        InventoryManager.inventory.add(product3);
    }

    @Test
    void testLoadInventoryFromFile() throws IOException {
        String testData = "Chess,5,15.99,5678\nScrabble,3,25.99,9876\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter(InventoryManager.databaseFilePath));
        writer.write(testData);
        writer.close();

        InventoryManager.inventory.clear();
        InventoryManagerMethods.loadInventoryFromFile();

        assertEquals(2, InventoryManager.inventory.size());
        assertEquals("Chess", InventoryManager.inventory.get(0).name);
        assertEquals("Scrabble", InventoryManager.inventory.get(1).name);
    }

    @Test
    void testAddProduct() {
        String input = "Chess\n5\n12.99\n9999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);

        InventoryManagerMethods.addProduct();
        System.setIn(System.in);

        assertEquals(4, InventoryManager.inventory.size());
        assertEquals("Chess", InventoryManager.inventory.get(3).name);
        assertEquals("9999", InventoryManager.inventory.get(3).sku);
    }

    @Test
    void testRemoveProduct() {
        String input = "1125\n"; // Remove Monopoly
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);
        //Method Called
        InventoryManagerMethods.removeProduct();
        System.setIn(System.in);

        assertEquals(2, InventoryManager.inventory.size());
        assertFalse(InventoryManager.inventory.contains(product1));
    }

    @Test
    void testViewInventory() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //METHOD Called//
        InventoryManagerMethods.viewInventory();
        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("Monopoly | SKU:1125 | Quantity: 10 | Price: $5.99"));
        assertTrue(output.contains("Jenga | SKU:1124 | Quantity: 2 | Price: $19.99"));
        assertTrue(output.contains("Snake | SKU:2424 | Quantity: 2 | Price: $20.99"));
    }

    @Test
    void testSearchInventoryByName() {
        String input = "1\nMonopoly\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        InventoryManagerMethods.searchInventory();
        System.setOut(System.out);
        System.setIn(System.in);

        String output = outContent.toString();
        assertTrue(output.contains("Monopoly"));
    }

    @Test
    void testSearchInventoryBySKU() {
        String input = "2\n1124\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        InventoryManagerMethods.searchInventory();
        System.setOut(System.out);
        System.setIn(System.in);

        String output = outContent.toString();
        assertTrue(output.contains("Jenga"));
    }

    @Test
    void testEditProduct() {
        String input = "1125\n1\nMonopoly Deluxe\n3\n15\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);

        InventoryManagerMethods.editProduct();
        System.setIn(System.in);

        assertEquals("Monopoly Deluxe", product1.name);
        assertEquals(15, product1.quantity);
    }

    @Test
    void testSortInventoryByName() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);

        InventoryManagerMethods.sortInventory();
        System.setIn(System.in);

        assertEquals("Jenga", InventoryManager.inventory.get(0).name);
        assertEquals("Monopoly", InventoryManager.inventory.get(1).name);
        assertEquals("Snake", InventoryManager.inventory.get(2).name);
    }

    @Test
    void testSortInventoryBySKU() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);

        InventoryManagerMethods.sortInventory();
        System.setIn(System.in);

        assertEquals("Jenga", InventoryManager.inventory.get(0).name);
        assertEquals("Monopoly", InventoryManager.inventory.get(1).name);
        assertEquals("Snake", InventoryManager.inventory.get(2).name);
    }

    @Test
    void testLoginSuccessful() {
        String input = InventoryManager.Username + "\n" + InventoryManager.Password + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);

        assertTrue(InventoryManagerMethods.login());
        System.setIn(System.in);
    }

    @Test
    void testLoginFailed() {
        String input = "wrongUser\nwrongPass\nwrongUser\nwrongPass\nwrongUser\nwrongPass\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);

        assertFalse(InventoryManagerMethods.login());
        System.setIn(System.in);
    }
}
