import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class InventoryManagerMethodsTest {
    private Product product1, product2, product3;

    @BeforeEach
    void setUp() {
        // Initialize test inventory and scanner
        InventoryManager.inventory = new ArrayList<>();
        InventoryManager.databaseFilePath = "test_inventory.txt";

        ArrayList<String> tags1 = new ArrayList<>();
        tags1.add("Board Game");
        tags1.add("Hasbro");
        tags1.add("8");
        product1 = new Product("Monopoly", 10, 5.99, "1125", tags1);
        ArrayList<String> tags2 = new ArrayList<>();
        tags2.add("Board Game");
        tags2.add("Hasbro");
        tags2.add("8");
        product2 = new Product("Jenga", 2, 19.99, "1124", tags2);
        ArrayList<String> tags3 = new ArrayList<>();
        tags3.add("Board Game");
        tags3.add("Hasbro");
        tags3.add("8");
        product3 = new Product("Snake", 2, 20.99, "2424", tags3);

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
        String input = "Chess\n5\n12.99\n9999\nBoard Game\nUnknown\n5";
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
        String input = "1125\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InventoryManager.scanner = new Scanner(System.in);
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
        assertTrue(output.contains("Jenga | SKU:1124 | Quantity: 2 | Price: $19.99 | Tags: Board Game, Hasbro, 8"));
        assertTrue(output.contains("Monopoly | SKU:1125 | Quantity: 10 | Price: $5.99 | Tags: Board Game, Hasbro, 8"));
        assertTrue(output.contains("Snake | SKU:2424 | Quantity: 2 | Price: $20.99 | Tags: Board Game, Hasbro, 8"));
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
        String input = "1125\n1\nMonopoly Deluxe\n3\n15\n6\n";
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

//    First test to show "red"
//    @Test
//    void testAddMultipleItemsToCart() {
//        Cart cart = new Cart();
//        cart.addItem(product1);
//        cart.addItem(product2);
//
//        assertEquals(2, cart.getItems().size());
//        assertEquals(2, cart.getItems().get(0).quantity);
//        assertEquals(3, cart.getItems().get(1).quantity);
//    }

    @Test
    void testAddMultipleItemsToCart() {
        Product product1 = new Product("Toy Car", 2, 9.99, "1001", new ArrayList<>());
        Product product2 = new Product("Doll", 3, 14.99, "1002", new ArrayList<>());

        Cart cart = new Cart();
        cart.addItem(product1);
        cart.addItem(product2);

        assertEquals(2, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).quantity);
        assertEquals(3, cart.getItems().get(1).quantity);
    }

    @Test
    void testAddExistingProductToCart() {
        Product product1 = new Product("Toy Car", 2, 9.99, "1001", new ArrayList<>());
        Product product2 = new Product("Toy Car", 3, 9.99, "1001", new ArrayList<>());

        Cart cart = new Cart();
        cart.addItem(product1);
        cart.addItem(product2); // Adding more Toy Cars with the same SKU

        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).quantity); // The quantity of Toy Cars should now be 5
    }

    @Test
    void testAddNoItemToCart() {
        Cart cart = new Cart();
        assertEquals(0, cart.getItems().size()); // No items in the cart initially
    }
}
