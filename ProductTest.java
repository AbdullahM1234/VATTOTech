import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("HotWheels", 10, 5.99, "LAMBO1");
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
        String expected = "HOTWHEELS | SKU:LAMBO1 | Quantity: 10 | Price: $5.99";
        assertEquals(expected, product.toString());
    }

    @Test
    void testToFile() {
        String expected = "HOTWHEELS,10,5.99,LAMBO1";
        assertEquals(expected, product.toFile());
    }
}
