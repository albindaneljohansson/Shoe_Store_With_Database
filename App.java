import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private Repository repository;
    private List<Customer> customerList;
    private Scanner sc = new Scanner(System.in);
    private Customer tempCustomer;
    Orders_Factory orders_factory;

    public App() throws IOException {
        this.repository = new Repository();
        this.orders_factory = new Orders_Factory();
        this.customerList = repository.getCustomer();
        welcome();
    }

    private void welcome() throws IOException {
        while(true){
            showMainMenu();
            int input = receiveInput();
            handleMainMenu(input);
        }
    }

    private void logIn() throws IOException {
        while(true) {
            System.out.println("För att beställa måste du logga in.\nAnge förnamn:");
            String first_name = sc.nextLine();
            System.out.println("Ange efternamn:");
            String last_name = sc.nextLine();
            System.out.println("Ange lösenord:");
            String password = sc.nextLine();

            if(customerList.stream().anyMatch(c -> c.getFirst_name().equalsIgnoreCase(first_name)
                    && c.getLast_name().equalsIgnoreCase(last_name) && c.getCustomer_password().equals(password))){

                Optional<Customer> optCustomer = customerList.stream()
                        .filter(c -> c.getFirst_name().equalsIgnoreCase(first_name))
                        .filter(c -> c.getLast_name().equalsIgnoreCase(last_name))
                        .filter(c -> c.getCustomer_password().equals(password))
                        .findFirst();

                this.tempCustomer = optCustomer.get();
                createOrder();
            }
            else failedLogIn();
        }
    }

    private void failedLogIn () throws IOException {
        while (true) {
            System.out.println("Inloggingen misslyckades. Vill du försöka igen?");
            System.out.println("Ja/Nej");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Ja")) {
                logIn();
            }
            if (answer.equalsIgnoreCase("Nej")) {
                welcome();
            }
        }
    }

    private void getSalesSupport() throws IOException {
        SalesSupport s = new SalesSupport();
        s.welcomeToSales(repository);
    }

    private void createOrder() throws IOException {
        while (true){
            System.out.println(orders_factory.create_New_Order(tempCustomer, repository));
            showOrderMenu();
            int input = receiveInput();
            if(!handleOrderMenu(input)){
                welcome();
            }
        }
    }

    private void showMainMenu() {
        System.out.println("""
                
                -------MENU--------
                1. Beställ vara
                2. Säljstöd
                3. Avsluta
                -------------------
                
                """);
    }

    private void showOrderMenu() {
        System.out.println("""
                
                -------MENU--------
                1. Lägg till en order
                2. Logga ut
                -------------------
                
                """);
    }

    private void handleMainMenu(int input) throws IOException {
        switch (input) {
            case 1 -> logIn();
            case 2 -> getSalesSupport();
            case 3 -> System.exit(0);
        }
    }

    private boolean handleOrderMenu(int input) throws IOException {
        switch (input) {
            case 1 -> createOrder();}
        return false;
    }

    private int receiveInput() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        System.out.print("Ange val: ");
        while (scanner.hasNextInt()) {
            input = scanner.nextInt();
            break;
        }
        return input;
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
    }
}
