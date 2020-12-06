import java.util.HashMap;

public class Shop {
    private static int Count = 0;
    private int ID;
    private String Name;
    private String Address;
    private HashMap<Integer, RetailProduct> Goods = new HashMap<>();

    public Shop(String Name, String Address) {
        Count++;
        this.ID = Count;
        this.Name = Name;
        this.Address = Address;
    }

    public void shipConsignment(Consignment cons) {
        for(RetailProduct i : cons.consignment) {
            Goods.put(i.getID(), i);
        }
    }

    public Consignment maxPurchase(double money) {
        Consignment purchase = new Consignment();
        int amount;
        for(RetailProduct i : Goods.values()) {
            amount = (int)Math.floor(money/i.getPrice());
            if(i.getAmount() < amount) {
                amount = i.getAmount();
            }
            if(amount == 0)
                continue;
            purchase.addRetailProduct(i, amount);
        }
        return purchase;
    }

    public double buySome(Cart c) throws Throwable {
        double sum = 0;
        for(Product i : c.cart.keySet()) {
            if(this.Goods.get(i.getID()).getAmount() < c.cart.get(i)) {
                throw new Throwable();
            }
            else {
                sum += this.Goods.get(i.getID()).getPrice() * c.cart.get(i);
            }
        }
        return sum;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.Name;
    }

    public double getPrice(Product product) throws Throwable {
        try {
            return this.Goods.get(product.getID()).getPrice();
        } catch(Throwable t) {
            throw t;
        }
    }
}
