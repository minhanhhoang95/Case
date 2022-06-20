package service;

import model.OrderItem;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOderItemService {
    private static final String PATH = "D:\\Module2\\Case\\Java\\data\\order-items.csv";
    private static OrderItemService instance;

    private OrderItemService() {

    }

    public static OrderItemService getInstance() {
        if (instance == null)
            instance = new OrderItemService();
        return instance;
    }


    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItem = new ArrayList<>();
        List<String> records = CSVUtils.readData(PATH);
        for (String record : records) {
            orderItem.add(new OrderItem(record));
        }
        return orderItem;
    }

    @Override
    public void add(OrderItem newOrderItem) {
        List<OrderItem> orderItems = findAll();
        orderItems.add(newOrderItem);
        CSVUtils.writeData(PATH, orderItems);
    }

    @Override
    public void update(OrderItem newOrderItem) {
        List<OrderItem> orderItems = findAll();
        CSVUtils.writeData(PATH, orderItems);

    }

    @Override
    public OrderItem getOrderItemById(int id) {
        List<OrderItem> orderItems = findAll();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId() == id) {
                return orderItem;
            }
        }
        return null;
    }
}
