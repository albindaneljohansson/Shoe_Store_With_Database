public class Order_line {
    private int id;
    private Orders order_id;
    private Shoe shoe_id;
    private int quantity;

    public Order_line(){}

    public Order_line(int id, Orders order_id, Shoe shoe_id, int quantity) {
        this.id = id;
        this.order_id = order_id;
        this.shoe_id = shoe_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Orders order_id) {
        this.order_id = order_id;
    }

    public Shoe getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(Shoe shoe_id) {
        this.shoe_id = shoe_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected String getModel(){
        return this.getShoe_id().getModel();
    }
    protected String getNameAndAddressFromOrder_line(){
        return this.getOrder_id().getCustomerNameAdressFromOrders();
    }
}
