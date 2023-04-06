public class Shoe {
    private int id;
    private int price;
    private String model;
    private Size size_id;
    private Brand brand_id;
    private Color color_id;
    private int stock_balance;

    public Shoe(){}

    public Shoe(int id, int price, String model, Size size_id, Brand brand_id, Color color_id, int stock_balance) {
        this.id = id;
        this.price = price;
        this.model = model;
        this.size_id = size_id;
        this.brand_id = brand_id;
        this.color_id = color_id;
        this.stock_balance = stock_balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Size getSize_id() {
        return size_id;
    }

    public void setSize_id(Size size_id) {
        this.size_id = size_id;
    }

    public Brand getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Brand brand_id) {
        this.brand_id = brand_id;
    }

    public Color getColor_id() {
        return color_id;
    }

    public void setColor_id(Color color_id) {
        this.color_id = color_id;
    }

    public int getStock_balance() {
        return stock_balance;
    }

    public void setStock_balance(int stock_balance) {
        this.stock_balance = stock_balance;
    }


    public String getData() {
        return "Shoe{" +
                "price=" + price +
                ", model='" + model + '\'' +
                ", size=" + size_id.getSize() +
                ", Brand" + brand_id.getBrand_name() +
                ", Color" + color_id.getColor() +
                '}';
    }
}
