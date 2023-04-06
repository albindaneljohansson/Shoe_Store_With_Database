import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Orders_Factory {

    private Scanner sc = new Scanner(System.in);
    private Repository repository;
    private Shoe tempShoe;

    protected String create_New_Order(Customer c, Repository r) throws IOException {

        this.repository = r;
        int order_id = r.getOrders().size() + 1;
        tempShoe = addShoeToOrder();
        String feedback = "";

        while(true) {

            System.out.println("Ange antal:");
            int quantity = sc.nextInt();
            sc.nextLine();
            for (int i=0; i<quantity; i++) {
                feedback = r.callAddToCart(c.getId(), order_id, tempShoe.getId(), c.getFirst_name());
            }

            System.out.println(quantity+ "st -- "+tempShoe.getBrand_id().getBrand_name() +
                    " "+ tempShoe.getModel()+" -- adderad till din order!");
            System.out.println("vill du beställa fler produkter?");
            System.out.println("Ja/Nej");
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("Ja")) {
                tempShoe= addShoeToOrder();
            }
            if (answer.equalsIgnoreCase("Nej")) {
                return feedback;
            }
        }
    }

    private Shoe addShoeToOrder() throws IOException {
        List<Shoe> shoeList = repository.getShoe();

        while(true) {
            System.out.println("\nAnge val för sko:");
            AtomicInteger count = new AtomicInteger(0);

            shoeList.stream()
                    .filter(s -> s.getStock_balance() > 0)
                    .forEach(s -> System.out.println("Produkt " + count.incrementAndGet() +
                            "  -  Märke: " + s.getBrand_id().getBrand_name() + "  Modell: " + s.getModel() +
                            "\t  Storlek: " + s.getSize_id().getSize() + "  Färg: " + s.getColor_id().getColor() +
                            "  -  PRIS: " + s.getPrice() + " Kr"));

            System.out.println("\nVilken produkt vill du lägga till? Välj produktnummer:");
            int productChoice = sc.nextInt();
            sc.nextLine();
            if (productChoice <= shoeList.size()) {
                return shoeList.get(productChoice - 1);
            }
        }
    }
}
