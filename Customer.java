public class Customer {
    private int id;
    private String first_name;
    private String last_name;
    private String address;
    private String customer_password;

    public Customer(){}

    public Customer(int id, String first_name, String last_name, String address, String customer_password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.customer_password = customer_password;
    }

    public String getFullName(){
        return first_name+" "+last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    protected String getFullNameAndAddress(){
        return this.getFullName()+" - "+this.address;
    }


}
