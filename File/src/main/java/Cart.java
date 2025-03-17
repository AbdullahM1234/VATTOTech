import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        for (Product existingProduct : items) {
            if (existingProduct.sku.equals(product.sku)) {
                existingProduct.quantity += product.quantity;
                return;
            }
        }
        items.add(product);
    }
    public List<Product> getItems() {
        return items;
    }
}
