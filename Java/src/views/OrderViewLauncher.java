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
    public static void run(){
        Scanner sc = new Scanner(System.in);
        menuOrder();
//        OrderView orderView = new OrderView();
        System.out.println(" Enter your choice: :");
        int option = Integer.parseInt(sc.nextLine());
        switch (option){
            case 1 :
//                orderView.addOrder();
                break;
            case 2 :
//                orderView.showOrderList();
                break;
            case 3 :
                MenuView.showMainMenu();
                break;
            case 4:
                AppUtils.exit();
                break;
            default:
                System.out.println("Wrong input. Try again please!");
                run();
        }
    }
}
