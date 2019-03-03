package products;

import com.github.javafaker.Faker;

public class Product {

    Faker faker = new Faker();

    private String name;
    private String code;
    private int quantity;
    private String price;

    public Product(){
        this.name = faker.commerce().productName();
        this.code = faker.commerce().promotionCode();
        this.quantity = faker.number().numberBetween(1,10);
        this.price = faker.commerce().price();
    }


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
