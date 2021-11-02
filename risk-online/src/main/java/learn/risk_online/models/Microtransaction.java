package learn.risk_online.models;

import java.util.Objects;

public class Microtransaction {
     private int id;
     private String product;
     private int price; //change to BigDecimal?

    public Microtransaction() {
    }

    public Microtransaction(int id, String product, int price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Microtransaction that = (Microtransaction) o;
        return id == that.id && price == that.price && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, price);
    }
}
