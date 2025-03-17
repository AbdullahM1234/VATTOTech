import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("Cars");
        tags.add("HotWheels");
        tags.add("5");
        product = new Product("HotWheels Lambo", 10, 5.99, "LAMBO1", tags);
    }

    @Test
    void testProductCreation() {
        assertEquals("HotWheels", product.name);
        assertEquals(10, product.quantity);
        assertEquals(5.99, product.price);
        assertEquals("LAMBO1", product.sku);
    }

    @Test
    void testToString() {
        String expected = "HotWheels | SKU:LAMBO1 | Quantity: 10 | Price: $5.99 | Tags: Cars, HotWheels, 5";
        assertEquals(expected, product.toString());
    }

    @Test
    void testToFile() {
        String expected = "HotWheels,10,5.99,LAMBO1,Cars,HotWheels,5";
        assertEquals(expected, product.toFile());
    }
}
