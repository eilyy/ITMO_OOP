import java.util.HashMap;

public class Services {
    public static HashMap<Integer, Shop> Shops = new HashMap<>();

    public static Shop getCheapest(Product Product) throws Throwable {
        double min = Double.MAX_VALUE;
        Shop minShop = null;
        for(Shop i : Shops.values()) {
            try {
                if(i.getPrice(Product) < min) {
                    min = i.getPrice(Product);
                    minShop = i;
                }
            } catch(Throwable t) {
                continue;
            }
        }
        if(minShop == null) {
            throw new Throwable();
        }
        return minShop;
    }

    public static Shop getCheapestCart(Cart c) throws Throwable {
        double min = Double.MAX_VALUE;
        Shop minShop = null;
        for(Shop i : Shops.values()) {
            try {
                if(i.buySome(c) < min) {
                    min = i.buySome(c);
                    minShop = i;
                }
            } catch(Throwable t) {
                continue;
            }
        }
        if(minShop == null) {
            throw new Throwable();
        }
        return minShop;
    }
}
