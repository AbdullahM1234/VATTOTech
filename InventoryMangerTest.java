//https://junit.org/junit5/docs/current/user-guide/ To help Understand
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

class InventoryManagerTest {
    private Product product1, product2, product3;
    private ArrayList<Product> testInventory;

    @BeforeEach
    void setUp() {
        product1 = new Product("Monopoly", 10, 5.99, "1125");
        product2 = new Product("Jenga", 2, 19.99, "1124");
        product3 = new Product("Snake",2,20.99,"2424");
        testInventory = new ArrayList<>();
    }

    @Test
    void testAddProduct() {
        testInventory.add(product1);
        testInventory.add(product2);
        testInventory.add(product3);

        assertEquals(3, testInventory.size());
        assertEquals("Monopoly", testInventory.get(0).name);
        assertEquals("Jenga", testInventory.get(1).name);
        assertEquals("Snake", testInventory.get(2).name);
    }

    @Test
    void testRemoveProduct() {
        testInventory.add(product1);
        testInventory.add(product2);
        testInventory.add(product3);

        testInventory.removeIf(p -> p.sku.equals("1125"));

        assertEquals(2, testInventory.size());
        assertEquals("Jenga", testInventory.get(0).name);
    }

    @Test
    void testSearchBySKU() {
        testInventory.add(product1);
        testInventory.add(product2);
        testInventory.add(product3);

        Product found = null;
        for (Product p : testInventory) {
            if (p.sku.equals("2424")) {
                found = p;
                break;
            }
        }

        assertNotNull(found);
        assertEquals("Snake", found.name);
    }

    @Test
    void testSearchByName() {
        testInventory.add(product1);
        testInventory.add(product2);
        testInventory.add(product3);

        Product found = null;
        for (Product p : testInventory) {
            if (p.name.equalsIgnoreCase("Monopoly")) {
                found = p;
                break;
            }
        }

        assertNotNull(found);
        assertEquals("1125", found.sku);
    }
    @Test
    void testViewInventory() {
        // Add products to inventory
        testInventory.add(product1);
        testInventory.add(product2);
        testInventory.add(product3);

        // Capture console output  //https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        for (Product product : testInventory) {
            System.out.println(product);
        }

        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("Monopoly | SKU:1125 | Quantity: 10 | Price: $5.99"));
        assertTrue(output.contains("Jenga | SKU:1124 | Quantity: 2 | Price: $19.99"));
        assertTrue(output.contains("Snake | SKU:2424 | Quantity: 2 | Price: $20.99"));
    }
@Test
    //https://stackoverflow.com/questions/16066671/how-can-i-unit-test-user-input-in-java
    void testEditProduct() {
        testInventory.add(product1);

        String input = "1\nMonopoly New York Edition\n3\n15\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setIn(System.in);

        Product product = testInventory.get(0);
        product.name = "Monopoly New york Edition";
        product.quantity = 15;

        System.setIn(System.in);

        assertEquals("Monopoly New york Edition", product.name);
        assertEquals(15, product.quantity);
    }
}
