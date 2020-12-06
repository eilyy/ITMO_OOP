public class Product {
    private static int count = 0;
    private int ID;
    private String Name;

    public Product(String Name) {
        count++;
        this.ID = count;
        this.Name = Name;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.Name;
    }
}
