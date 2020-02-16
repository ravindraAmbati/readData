package com.bigbasket.readData.component;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
public class Product {
    private long id;
    private String prodId;
    private String name;
    private String quantity;
    private double price;
    private Timestamp timestamp;

    public Product() {
    }

    public Product(String prodId) {
        this.prodId = prodId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId() == product.getId() &&
                Double.compare(product.getPrice(), getPrice()) == 0 &&
                Objects.equals(getProdId(), product.getProdId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getQuantity(), product.getQuantity()) &&
                Objects.equals(getTimestamp(), product.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProdId(), getName(), getQuantity(), getPrice(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodId='" + prodId + '\'' +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}
