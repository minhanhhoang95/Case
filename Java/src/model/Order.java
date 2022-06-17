package model;

import utils.DateUtils;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private long id;
    private String name;
    private String phone;
    private String address;
    private Double grandTotal;
    private Instant createdAt;
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }


    private Order() {
    }


    public static Order parse(String row) {
        Order order = new Order();
        String[] oderInfo = row.split(",");
        order.id = Long.parseLong(oderInfo[0]);
        order.name = oderInfo[1];
        order.phone = oderInfo[2];
        order.address = oderInfo[3];
        return order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phone + "," + address;
    }

}
