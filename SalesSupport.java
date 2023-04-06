

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class SalesSupport {

    private ShoeSearchInterFace colorSearch;
    private ShoeSearchInterFace sizeSearch;
    private ShoeSearchInterFace brandSearch;
    private final Scanner sc = new Scanner(System.in);
    private Repository rep;
    private List<Orders> ordersList;
    List<Order_line> orderLineList;
    private Boolean sales;

    protected void welcomeToSales(Repository r) throws IOException {
        this.rep = r;
        this.ordersList = rep.getOrders();
        this.orderLineList = rep.getOrder_lines();
        this.sales=true;
        while(sales){
            showMainMenu();
            int input = receiveInput();
            handleMainMenu(input);
        }
    }

    private void callAdvancedSearch() throws IOException {
        while(true) {
            showSearchMenu();
            int input = receiveInput();
            if (input > 0 && input <4) {
                advancedSearch(input);
            }
            if(input == 4){
                break;}
        }
    }

    private void advancedSearch(int attributeToSearchFor) throws IOException {
        initSearchFunctions();
        System.out.println("Ange sökord:");
        String wordToSearchFor = sc.nextLine();

        if (attributeToSearchFor == 1){
            printResults(searchShoe(wordToSearchFor, colorSearch));
        }
        if (attributeToSearchFor == 2){
            printResults(searchShoe(wordToSearchFor, brandSearch));
        }
        else if (attributeToSearchFor == 3){
            int sizeToSearchFor = Integer.parseInt(wordToSearchFor);
            printResults(searchShoe(sizeToSearchFor, sizeSearch));
        }
    }

    private void printResults(List<Order_line> orderLinesList) {

        Map<String, List<Order_line>> GroupedByCustomer = orderLinesList.stream()
                .collect(groupingBy(o -> o.getNameAndAddressFromOrder_line()));
        GroupedByCustomer.forEach((customer, orders) -> System.out.println(customer));
    }

    // Här skickar vi in sökord och en av de färdiga sökfunktionerna
    private List<Order_line> searchShoe (Object searchWord, ShoeSearchInterFace ssi) throws IOException {
        return rep.getOrder_lines().stream().filter(s -> ssi.search(s, searchWord))
                .toList();
    }

    // Konstruktor för searchFunctions
    private void initSearchFunctions() {
        this.colorSearch = (ol, se) -> ol.getShoe_id().getColor_id().getColor().equalsIgnoreCase((String)se);
        this.brandSearch = (ol, se) -> ol.getShoe_id().getBrand_id().getBrand_name().equalsIgnoreCase((String)se);
        this.sizeSearch = (ol, se) -> ol.getShoe_id().getSize_id().getSize() == (int) se;
    }

    private void orderSumPerRegion() {

        Map<String, Integer> map =ordersList.stream()
                .collect(groupingBy(a -> a.getCustomerAdress(), summingInt(a -> a.getSales_tot())));
        map.forEach((address, orderSum) -> System.out.println(address + ": " + orderSum));
    }

    private void orderSumPerCustomer() {

        Map<String, Integer> map =ordersList.stream()
                .collect(groupingBy(a -> a.getCustomerFullname(), summingInt(a -> a.getSales_tot())));
        map.forEach((customer, orderSum) -> System.out.println(customer + ": " + orderSum));
    }

    private void ordersPerCustomer() {

        Map<String, List<Orders>> ordersGroupedByCustomer = ordersList.stream()
                .collect(groupingBy(order -> order.getCustomer_id().getFullName()));
        ordersGroupedByCustomer.forEach((customer, orders) -> System.out.println(customer + ": " + orders.size()));
    }

    private void topFiveProducts() {

        Map<String, Integer> map =orderLineList.stream()
                .collect(groupingBy(o -> o.getModel(), summingInt(o -> o.getQuantity())));

        Stream<Map.Entry<String,Integer>> sorted = map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(5);
        sorted.forEach(System.out::println);
    }

    private void handleMainMenu(int input) throws IOException {
        switch (input) {
            case 1 -> callAdvancedSearch();
            case 2 -> ordersPerCustomer();
            case 3 -> orderSumPerCustomer();
            case 4 -> orderSumPerRegion();
            case 5 -> topFiveProducts();
            case 6 -> sales = false;
        }
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


    private void showMainMenu() {
        System.out.println("""
                
                ---- Säljstöd ----
                
                1. Egen sökning
                2. Order per kund
                3. Ordersumma per kund
                4. Ordersumma per ort
                5. Topp 5 sålda produkter
                6. Huvudmenu
                -------------------
                
                """);
    }

    private void showSearchMenu() {
        System.out.println("""
                
                ---- MENU ----
                Sök på kunder utifrån:
                1. Inköp - Ange färg
                2. Inköp - Ange märke
                3. Inköp - Ange storlek
                4. Huvudmenu - Säljstöd
                -------------------
                
                """);
    }
}
