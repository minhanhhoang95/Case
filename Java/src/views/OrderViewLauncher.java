package views;

import utils.AppUtils;

import java.util.Scanner;

public class OrderViewLauncher {

    public static void menuOrder() {

        System.out.print("╔══════════════════════════════════════════════════════════════════════════════════════════════════╗" +
                "\n║                                                                                                  ║" +
                "\n║                                        Order Management                                          ║" +
                "\n║                                          [Main Menu]                                             ║" +
                "\n║                                                                                                  ║" +
                "\n║                               ■  [1]      Add Order                                              ║" +
                "\n║                               ■  [2]      Show Order                                             ║" +
                "\n║                               ■  [3]      Come Back                                              ║" +
                "\n║                               ■  [0]      Exit                                                   ║" +
                "\n║                                                                                                  ║" +
                "\n╚══════════════════════════════════════════════════════════════════════════════════════════════════╝" +
                "\n");
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        OrderView orderView = new OrderView();
        while (true) {
            try {
                menuOrder();
                System.out.println(" Enter your choice: :");
                int option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1:
                        orderView.addOrder();
                        break;
                    case 2:
                        orderView.showAllOrder();
                        break;
                    case 3:
                        MenuView.showMainMenu();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Wrong input. Try again please!");
                        run();
                }
            }catch (Exception e){
                System.err.println("Wrong input. Try again.");
            }

        }
    }

//    public static void main(String[] args) {
//        OrderViewLauncher.run();
//    }
}
