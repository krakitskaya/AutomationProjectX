package entity;

import java.util.Objects;

public class ProductItem {

    private String name;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem productItem = (ProductItem) o;
        return Objects.equals(name, productItem.name) && Objects.equals(price, productItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
