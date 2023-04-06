public class Brand {
    private int id;
    private String brand_name;

    public Brand(){}

    public Brand(int id, String brand_name) {
        this.id = id;
        this.brand_name = brand_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }
}
