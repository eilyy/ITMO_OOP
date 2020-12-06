import java.util.HashMap;

public class Cart {
    HashMap<Product, Integer> cart = new HashMap<>();

    public void addProduct(Product prod, int count) {
        cart.put(prod, count);
    }
}
