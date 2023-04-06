import java.util.Date;

public class Orders {
    private int id;
    private Customer customer_id;
    private String order_date;
    private int sales_tot;

    public Orders(){}

    public Orders(int id, Customer customer_id, String order_date, int sales_tot) {
        this.id = id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.sales_tot = sales_tot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getSales_tot() {
        return sales_tot;
    }

    public void setSales_tot(int sales_tot) {
        this.sales_tot = sales_tot;
    }

    protected String getCustomerFullname(){
        return this.customer_id.getFullName();
    }
    protected String getCustomerAdress(){
        return this.getCustomer_id().getAddress();
    }

    protected String getCustomerNameAdressFromOrders(){
        return this.getCustomer_id().getFullNameAndAddress();
    }
}
