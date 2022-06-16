package model;

import utils.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private long id;
    private String name;
    private String phone;

    private Date createDate;
    private List<OrderItem> orderItems = new ArrayList<>();

    private Order() {
    }

    public Order(String row) {
        String[] oderInfo = row.split(",");
        id = Long.parseLong(oderInfo[0]);
        name = oderInfo[1];
        phone = oderInfo[2];
        createDate = DateUtils.stringToDate(oderInfo[3]);
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


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return id +
                "," + name +
                "," + phone +
                "," + DateUtils.dateToString(createDate);

    }

}
