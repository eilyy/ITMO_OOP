import java.util.Vector;

public class Consignment {
    Vector<RetailProduct> consignment = new Vector<>();

    public void addProduct(Product prod, int amount, double price) {
        consignment.add(new RetailProduct(prod.getID(), prod.getName(), price, amount));
    }

    public void addRetailProduct(RetailProduct retailProduct, int amount) {
        retailProduct.setAmount(amount);
        consignment.add(retailProduct);
    }

    public String makeOutput() {
        String output = "";
        for(RetailProduct i : consignment) {
            output += "Name: " + i.getName() + "   Amount: " + i.getAmount() + '\n';
        }
        return output.substring(0, output.length() - 1);
    }
}
