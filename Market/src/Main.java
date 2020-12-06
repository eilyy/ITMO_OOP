import java.util.HashMap;

public class Main {

    public static void main(String args[]) {
        Shop sh1 = new Shop("Pyaterochka", "SPb");
        Services.Shops.put(sh1.getID(), sh1);

        Shop sh2 = new Shop("Magnit", "Krasnodar");
        Services.Shops.put(sh2.getID(), sh2);

        Shop sh3 = new Shop("Azbuka Vkusa", "Moscow");
        Services.Shops.put(sh3.getID(), sh3);

        Product pr1 = new Product("Cucumber");
        Product pr2 = new Product("Tomato");
        Product pr3 = new Product("Banana");
        Product pr4 = new Product("Apple");
        Product pr5 = new Product("Carrot");
        Product pr6 = new Product("Cookies");
        Product pr7 = new Product("Pineapple");
        Product pr8 = new Product("Coconut");
        Product pr9 = new Product("Cabbage");
        Product pr10 = new Product("Playstation 5");

        Consignment co1 = new Consignment();
        co1.addProduct(pr1, 20, 60);
        co1.addProduct(pr2, 15, 80);
        co1.addProduct(pr3, 5, 100);
        co1.addProduct(pr4, 10, 90);
        co1.addProduct(pr6, 5, 111.11);
        co1.addProduct(pr7, 3, 200);
        co1.addProduct(pr8, 5, 250);
        co1.addProduct(pr9, 18, 63);
        co1.addProduct(pr10, 1, 44990);

        Consignment co2 = new Consignment();
        co2.addProduct(pr1, 10, 80);
        co2.addProduct(pr2, 7, 90);
        co2.addProduct(pr3, 2, 105);
        co2.addProduct(pr4, 5, 90);
        co2.addProduct(pr5, 10, 42);
        co2.addProduct(pr6, 3, 147);
        co2.addProduct(pr7, 2, 300);
        co2.addProduct(pr8, 3, 320);
        co2.addProduct(pr9, 9, 95);

        Consignment co3 = new Consignment();
        co3.addProduct(pr6, 5, 310);
        co3.addProduct(pr7, 3, 400);
        co3.addProduct(pr8, 5, 520);
        co3.addProduct(pr10, 1, 62990);

        Cart ca1 = new Cart();
        ca1.addProduct(pr5, 2);
        ca1.addProduct(pr10, 2);

        sh1.shipConsignment(co1);
        sh2.shipConsignment(co2);
        sh3.shipConsignment(co3);
        System.out.println("==== TASK 4 ====");
        try {
            System.out.println(Services.getCheapest(pr10).getName());
        } catch(Throwable e) {
            System.err.print("There are no shops with given good in stock\n");
        }
        System.out.println("==== TASK 5 ====");
        System.out.println(sh3.maxPurchase(100000).makeOutput());
        System.out.println("==== TASK 6 ====");
        try {
            System.out.println(sh2.buySome(ca1));
        } catch(Throwable e) {
            System.err.print("Not enough needed goods in stock\n");
        }
        System.out.println("==== TASK 7 ====");
        try {
            System.out.println(Services.getCheapestCart(ca1).getName());
        } catch (Throwable e) {
            System.err.print("There are no shops with all given goods in stock\n");
        }
    }
}
