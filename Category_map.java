public class Category_map {
    private int id;
    private Shoe shoe_id;
    private Category cat_it;

    public Category_map(){}

    public Category_map(int id, Shoe shoe_id, Category cat_it) {
        this.id = id;
        this.shoe_id = shoe_id;
        this.cat_it = cat_it;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shoe getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(Shoe shoe_id) {
        this.shoe_id = shoe_id;
    }

    public Category getCat_it() {
        return cat_it;
    }

    public void setCat_it(Category cat_it) {
        this.cat_it = cat_it;
    }
}
