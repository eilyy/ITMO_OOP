public class RetailProduct {
    private int ID;
    private String Name;
    private double Price;
    private int Amount;

    public RetailProduct(int id, String name, double price, int amount) {
        this.ID = id;
        this.Name = name;
        this.Price = price;
        this.Amount = amount;
    }

    public int getID() {
        return this.ID;
    }

    public double getPrice() {
        return this.Price;
    }

    public void setAmount(int amount) {
        this.Amount = amount;
    }

    public int getAmount() {
        return this.Amount;
    }

    public String getName() {
        return this.Name;
    }
}
