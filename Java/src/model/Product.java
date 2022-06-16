package model;

import java.util.Objects;

public class Product {
    private long id;
    private String title;
    private int quantity;
    private double price;

    public Product(long id, String title, int quantity, double price) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String row) {
        String[] productInformation = row.split(",");
        this.id = Integer.parseInt(productInformation[0]);
        this.title = productInformation[1];
        this.price = Double.parseDouble(productInformation[2]);
        this.quantity = Integer.parseInt(productInformation[3]);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + price + "," + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && quantity == product.quantity && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, quantity);
    }
}
