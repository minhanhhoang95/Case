package views;

import model.Order;
import model.OrderItem;
import model.Product;
import service.*;
import utils.AppUtils;
import utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final IProductService productService;
    private final IOrderService orderService;
    private final IOderItemService oderItemService;
    private final Scanner sc = new Scanner(System.in);

    public OrderView() {
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
        oderItemService = OrderItemService.getInstance();
    }

    public boolean checkQuantityWine(Product product, int quantity) {
        if (quantity <= product.getQuantity()) {
            return true;
        } else {
            return false;
        }
    }

    public OrderItem addOrderItem(long orderId) {

//        OrderItem  orderItem = (OrderItem) orderItems;
//        List<OrderItem> orderItems = new ArrayList<>();
        do {
            try {
                oderItemService.findAll();
                ProductView productView = new ProductView();
                productView.showProductsClone();
                long id = System.currentTimeMillis() / 1000;
                System.out.println("Enter Wine Id : ");
                System.out.print(" ⭆ ");
                int wineId = sc.nextInt();
                sc.nextLine();
                while (!productService.existsById(wineId)) {
                    System.out.println("Id does not exist");
                    System.out.println("Please enter again: ");
                    System.out.print(" ⭆ ");
                    wineId = sc.nextInt();
                }
                Product product = productService.findById(wineId);
                double price = product.getPrice();
                int oldQuantity = product.getQuantity();
                System.out.println("Enter Quantity : ");
                System.out.print(" ⭆ ");
                int quantity = sc.nextInt();
                sc.nextLine();
                while (!checkQuantityWine(product, quantity)) {
                    System.out.println("Exceeding the quantity! Please enter again");
                    System.out.println("Enter Quantity");
                    System.out.print(" ⭆ ");
                    quantity = sc.nextInt();
                }
                String wineName = product.getTitle();
                double total = quantity * price;
                int currentQuantity = oldQuantity - quantity;
                product.setQuantity(currentQuantity);
                OrderItem orderItem = new OrderItem(id, price, quantity, orderId, wineId, wineName, total);
//                orderItems.add(orderItem);

                return orderItem;

            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!!");
            }
        } while (true);


    }

    public void addOrder() {
        try {
            orderService.findAll();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Enter your FullName (eg: Hoang Anh Minh)");
            System.out.print(" ⭆ ");
            String name = sc.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Name" + name + " Malformed." + " Please re-enter!" + "(Name must be capitalized and not accented)");
                System.out.println("Enter your name (eg: Hoang Anh Minh)");
                System.out.print(" ⭆ ");
                name = sc.nextLine();
            }
            System.out.println("Enter phone number (eg: 0394249825)");
            System.out.print(" ⭆ ");
            String phone = sc.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Number" + phone + "yours is not correct. Please re-enter!" + "(Phone number consists of 10 numbers and starts with 0)");
                System.out.println("Enter phone number (eg: 0394249825)");
                System.out.print(" ⭆ ");
                phone = sc.nextLine();
            }
            OrderItem orderItem = addOrderItem(orderId);
            Order order = new Order(orderId, name, phone);
            oderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Successful order creation");
            do {
                System.out.print("╔═══════════════════════════════════════════════════════════════════════════════╗" +
                        "\n║                                                                               ║" +
                        "\n║          ■  [1]   Press 'y' to create another order                           ║" +
                        "\n║          ■  [2]   Press 'q' to go back                                        ║" +
                        "\n║          ■  [3]   press 'p' to print the invoice                              ║" +
                        "\n║          ■  [4]   Press 't' to exit                                           ║" +
                        "\n║                                                                               ║" +
                        "\n╚═══════════════════════════════════════════════════════════════════════════════╝" +
                        "\n");
                System.out.print(" ⭆ ");
                String choice = sc.nextLine();
                switch (choice) {
                    case "y":
                        addOrder();
                        break;
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "p":
                        showPayment(orderItem, order);
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Invalid input! Please enter again");

                }

            } while (true);

        } catch (Exception e) {
            System.out.println("Syntax error. Please enter again");
        }
    }

    public void showPayment(OrderItem orderItem, Order order) {
        try {
            System.out.println("════════════════════════════════════════════════ User List ════════════════════════════════════════════════");
            System.out.printf("%-15s %-20s %-15s %-25s %-15s %-15s\n", "Id", "Customer Name", "Phone", "Wine ", "Quantity", "Price");
            System.out.printf("%-15d %-20s %-15s %-25s %-15d %-15f \n",
                    order.getId(),
                    order.getName(),
                    order.getPhone(),
                    orderItem.getProductName(),
                    orderItem.getQuantity(),
                    orderItem.getPrice()
            );
            System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
            System.out.println("════════════════════════════════════════════════ Bar DeBay ════════════════════════════════════════════════");
            System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
            boolean is = true;
            do {
                System.out.println("Press 'q' to go back\t|\t press 't' to exit the program");
                System.out.print(" ⭆ ");
                String choice = sc.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Syntax error ! Please enter again");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.findAll();
        List<OrderItem> orderItems = oderItemService.findAll();
        OrderItem newItem = new OrderItem();
        try {
            System.out.println("════════════════════════════════════════════════ Wine List ════════════════════════════════════════════════");
            System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
            System.out.printf("%-15s %-20s %-15s %-25s %-12s %-15s %-21s\n",
                    "   Id",
                    "Customer Name",
                    "Phone",
                    "Wine",
                    "Quantity",
                    "Price",
                    "Total" + "               ");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15d %-20s %-15s %-25s %-12d %-15f %-21f %-7s\n",
                        order.getId(),
                        order.getName(),
                        order.getPhone(),
                        newItem.getProductName(),
                        newItem.getQuantity(),
                        newItem.getPrice(),
                        newItem.getPrice() * newItem.getQuantity(),
                        "");
            }
            System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════ ");
            System.out.println("════════════════════════════════════════════════ Bar DeBay ════════════════════════════════════════════════ ");
            System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════ ");
            boolean is = true;
            do {
                System.out.println("Press 'q' to go back\t|\t press 't' to exit the program");
                System.out.print(" ⭆ ");
                String choice = sc.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Syntax error ! Please enter again");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
}
