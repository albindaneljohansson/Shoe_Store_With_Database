import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
    Properties p = new Properties();

    public List<Brand> getBrand() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, brand_name FROM Brand");
            List<Brand> brandList = new ArrayList<>();

            while (rs.next()) {
                Brand tempBrand = new Brand();
                int id = rs.getInt("id");
                tempBrand.setId(id);
                String brand_name = rs.getString("brand_name");
                tempBrand.setBrand_name(brand_name);
                brandList.add(tempBrand);
            }
            return brandList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Color> getColor() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, color FROM Color");
            List<Color> colorList = new ArrayList<>();

            while (rs.next()) {
                Color tempColor = new Color();
                int id = rs.getInt("id");
                tempColor.setId(id);
                String color = rs.getString("color");
                tempColor.setColor(color);
                colorList.add(tempColor);
            }
            return colorList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Size> getSize() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, size FROM Size");
            List<Size> SizeList = new ArrayList<>();

            while (rs.next()) {
                Size tempSize = new Size();
                int id = rs.getInt("id");
                tempSize.setId(id);
                int size = rs.getInt("size");
                tempSize.setSize(size);
                SizeList.add(tempSize);
            }
            return SizeList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Shoe> getShoe() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, price, model, size_id, " +
                    "brand_id, color_id, stock_balance FROM Shoe");
            List<Shoe> ShoeList = new ArrayList<>();

            while (rs.next()) {
                Shoe tempShoe = new Shoe();
                List<Size> sizeList = getSize();
                List<Brand> brandList = getBrand();
                List<Color> colorList = getColor();

                int id = rs.getInt("id");
                tempShoe.setId(id);
                int price = rs.getInt("price");
                tempShoe.setPrice(price);
                String model = rs.getString("model");
                tempShoe.setModel(model);
                int size_id = rs.getInt("size_id");

                for (Size s : sizeList){
                    if (s.getId() == size_id){
                        tempShoe.setSize_id(s);
                    }
                }
                int brand_id = rs.getInt("brand_id");
                for (Brand b : brandList){
                    if (b.getId() == brand_id){
                        tempShoe.setBrand_id(b);
                    }
                }
                int color_id = rs.getInt("color_id");
                for (Color c : colorList){
                    if (c.getId() == color_id){
                        tempShoe.setColor_id(c);
                    }
                }
                int stock_balance = rs.getInt("stock_balance");
                tempShoe.setStock_balance(stock_balance);

                ShoeList.add(tempShoe);
            }
            return ShoeList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> getCategory() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, category FROM Category");
            List<Category> categoryList = new ArrayList<>();

            while (rs.next()) {
                Category tempCategory = new Category();
                int id = rs.getInt("id");
                tempCategory.setId(id);
                String category = rs.getString("category");
                tempCategory.setCategory(category);
                categoryList.add(tempCategory);
            }
            return categoryList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category_map> getCategory_Map() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, shoe_id, cat_id FROM Category_map");
            List<Category_map> category_MapList = new ArrayList<>();

            while (rs.next()) {
                Category_map tempCategory_map = new Category_map();
                List<Shoe> shoeList = getShoe();
                List<Category> categoryList = getCategory();

                int id = rs.getInt("id");
                tempCategory_map.setId(id);
                int shoe_id = rs.getInt("shoe_id");
                for (Shoe s : shoeList) {
                    if (s.getId() == shoe_id) {
                        tempCategory_map.setShoe_id(s);
                    }
                }
                int cat_id = rs.getInt("cat_id");
                for (Category c : categoryList) {
                    if (c.getId() == cat_id) {
                        tempCategory_map.setCat_it(c);
                    }
                }

                category_MapList.add(tempCategory_map);
            }
            return category_MapList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomer() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, first_name, last_name, " +
                    "address, customer_password FROM Customer");
            List<Customer> CustomerList = new ArrayList<>();

            while (rs.next()) {
                Customer tempCustomer = new Customer();
                int id = rs.getInt("id");
                tempCustomer.setId(id);
                String first_name = rs.getString("first_name");
                tempCustomer.setFirst_name(first_name);
                String last_name = rs.getString("last_name");
                tempCustomer.setLast_name(last_name);
                String address = rs.getString("address");
                tempCustomer.setAddress(address);
                String customer_password = rs.getString("customer_password");
                tempCustomer.setCustomer_password(customer_password);

                CustomerList.add(tempCustomer);
            }
            return CustomerList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Orders> getOrders() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, customer_id, order_date, sales_tot FROM Orders");
            List<Orders> OrdersList = new ArrayList<>();

            while (rs.next()) {
                Orders tempOrders = new Orders();
                List<Customer> customerList = getCustomer();
                int id = rs.getInt("id");
                tempOrders.setId(id);
                int customer_id = rs.getInt("customer_id");
                for (Customer c : customerList) {
                    if (c.getId() == customer_id) {
                        tempOrders.setCustomer_id(c);
                    }
                }
                String order_date = rs.getString("order_date").toString();
                tempOrders.setOrder_date(order_date);
                int sales_tot = rs.getInt("sales_tot");
                tempOrders.setSales_tot(sales_tot);

                OrdersList.add(tempOrders);
            }
            return OrdersList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order_line> getOrder_lines() throws IOException {
        p.load(new FileInputStream("connection.properties"));
        try (Connection con = DriverManager.getConnection(p.getProperty("url")
                , p.getProperty("user"), p.getProperty("password"));) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select id, order_id, shoe_id, quantity FROM Order_line");
            List<Order_line> Order_lineList = new ArrayList<>();

            while (rs.next()) {
                Order_line tempOrder_line = new Order_line();
                List<Orders> orderList = getOrders();
                List<Shoe> shoeList = getShoe();
                int id = rs.getInt("id");
                tempOrder_line.setId(id);
                int Order_id = rs.getInt("order_id");
                for (Orders o : orderList) {
                    if (o.getId() == Order_id) {
                        tempOrder_line.setOrder_id(o);
                    }
                }
                int shoe_id = rs.getInt("shoe_id");
                for (Shoe s : shoeList) {
                    if (s.getId() == shoe_id) {
                        tempOrder_line.setShoe_id(s);
                    }
                }
                int quantity = rs.getInt("quantity");
                tempOrder_line.setQuantity(quantity);

                Order_lineList.add(tempOrder_line);
            }
            return Order_lineList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String callAddToCart(int customer_id, int order_id, int shoe_id, String name) throws IOException {
        p.load(new FileInputStream("connection.properties"));
        String feedback = "";


        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("user"),
                p.getProperty("password"));

             CallableStatement stm = con.prepareCall("CALL AddToCart(?,?,?)")) {
            stm.setInt(1, customer_id);
            stm.setInt(2, order_id);
            stm.setInt(3, shoe_id);
            stm.execute();
            feedback = "Ny order för "+name+" placerad!";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        return feedback;
    }
}
