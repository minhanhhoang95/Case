package views;

import model.Role;
import model.User;
import utils.AppUtils;

import java.util.List;
import java.util.Scanner;


public class MenuView {
    public static void login() {
        AdminView adminView = new AdminView();
        adminView.Login();
    }

    public static void showMainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("╔══════════════════════════════════════════════════════════════════════════════════════════════════╗" +
                "\n║                                                                                                  ║" +
                "\n║                                           Bar DeBay                                              ║" +
                "\n║                                          [Main Menu]                                             ║" +
                "\n║                                                                                                  ║" +
                "\n║                            ■  [1]      User Management                                           ║" +
                "\n║                            ■  [2]      Wine Management                                           ║" +
                "\n║                            ■  [3]      Order Management                                          ║" +
                "\n║                            ■  [0]      Exit                                                      ║" +
                "\n║                                                                                                  ║" +
                "\n╚══════════════════════════════════════════════════════════════════════════════════════════════════╝" +
                "\n■ Type a number above by your choice: ")
        ;
        int choice = -1;
        while (choice != 0) {
            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("User Management");
                        ManagerUserView.run();
                        break;
                    case 2:
                        System.out.println("Wine Management");
                        ManagerProductView.run();
                        break;
                    case 3:
                        System.out.println("Order Management");
                        OrderViewLauncher.run();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Wrong Input! Please enter a number !!! ");
                        showMainMenu();
                }
            } catch (Exception e) {
                System.err.println("Wrong input. Try again.");
            }
        }
    }


}
