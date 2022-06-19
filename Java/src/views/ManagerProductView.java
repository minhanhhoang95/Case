package views;

import utils.AppUtils;

import java.util.Scanner;

public class ManagerProductView {
    static Scanner sc = new Scanner(System.in);
    static ManagerProductView managerProductView = new ManagerProductView();

    public static void menuWine() {

        System.out.print("╔══════════════════════════════════════════════════════════════════════════════════════════════════╗" +
                "\n║                                                                                                  ║" +
                "\n║                                        Wine Management                                           ║" +
                "\n║                                          [Main Menu]                                             ║" +
                "\n║                                                                                                  ║" +
                "\n║                               ■  [1]      Add Wine                                               ║" +
                "\n║                               ■  [2]      Edit Wine                                              ║" +
                "\n║                               ■  [3]      Show Wine                                              ║" +
                "\n║                               ■  [4]      Remove Wine                                            ║" +
                "\n║                               ■  [5]      Sort Wine                                              ║" +
                "\n║                               ■  [6]      Come Back                                              ║" +
                "\n║                               ■  [0]      Exit                                                   ║" +
                "\n║                                                                                                  ║" +
                "\n╚══════════════════════════════════════════════════════════════════════════════════════════════════╝" +
                "\n")
        ;
    }

    public static void run() {
        ProductView productView = new ProductView();
        while (true) {
            try {
                menuWine();
                System.out.println("Enter your choice: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Add Product");
                        productView.add();
                        break;
                    case 2:
                        System.out.println("Edit Product by using ID");
                        productView.update();
                        break;
                    case 3:
                        System.out.println("Show Wine List");
                        productView.showProducts(InputOption.SHOW);
                        break;
                    case 4:
                        System.out.println("Remove Product by using ID");
                        productView.remove();
                        break;
                    case 5:
                        System.out.println("Sort Product by using name");
                        ManagerProductView.runSort();
                        break;
                    case 6:
                        System.out.println("Come back");
                        MenuView.showMainMenu();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.err.println("Wrong input. Try again.");
                }
            } catch (Exception e) {
                System.err.println("Wrong input. Try again.");
            }
        }
    }

    public static void menuSort() {
        System.out.print("╔══════════════════════════════════════════════════════════════════════════════════════════════════╗" +
                "\n║                                                                                                  ║" +
                "\n║                                          [Menu Sort]                                             ║" +
                "\n║                                                                                                  ║" +
                "\n║                               ■  [1]      Sort Ascending By Name                                 ║" +
                "\n║                               ■  [2]      Sort Descending By Name                                ║" +
                "\n║                               ■  [3]      Sort Ascending By Price                                ║" +
                "\n║                               ■  [4]      Sort Descending By Price                               ║" +
                "\n║                               ■  [5]      Come Back                                              ║" +
                "\n║                               ■  [0]      Exit                                                   ║" +
                "\n║                                                                                                  ║" +
                "\n╚══════════════════════════════════════════════════════════════════════════════════════════════════╝" +
                "\n")
        ;
    }

    public static void runSort() {
        ProductView productView = new ProductView();
        do {
            menuSort();
            try {
                System.out.println("Enter your choice : ");
                int choi = Integer.parseInt(sc.nextLine());
                switch (choi) {
                    case 1:
                        System.out.println("Sort Ascending By Name");
//                        hàm sort tăng dần theo tên
                        productView.sortByNameOrderByASC();
                        break;
                    case 2:
                        System.out.println("Sort Descending By Name");
//                        hàm sort giảm dần theo tên
                        productView.sortByNameOrderByDESC();
                        break;
                    case 3:
                        System.out.println("Sort Ascending By Price");
//                        hàm sort tăng dần theo Price
                        productView.sortByPriceOrderByASC();
                        break;
                    case 4:
                        System.out.println("Sort Descending By Price");
//                        hàm sort giảm dần theo Price
                        productView.sortByPriceOrderByDESC();
                        break;
                    case 5:
                        System.out.println("Come Back");
                        ManagerProductView.run();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Wrong choice ! Try again !!!");
                        runSort();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong input ! Try again !!!");
                runSort();
            }
        } while (true);
    }
}
