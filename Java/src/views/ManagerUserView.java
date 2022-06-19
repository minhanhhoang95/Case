package views;

import utils.AppUtils;

import java.util.Scanner;

public class ManagerUserView {
    static Scanner sc = new Scanner(System.in);


    public static void menuUser() {
        System.out.print("╔══════════════════════════════════════════════════════════════════════════════════════════════════╗" +
                "\n║                                                                                                  ║" +
                "\n║                                        User Management                                           ║" +
                "\n║                                          [Main Menu]                                             ║" +
                "\n║                                                                                                  ║" +
                "\n║                               ■  [1]      Add User                                               ║" +
                "\n║                               ■  [2]      Edit User                                              ║" +
                "\n║                               ■  [3]      Show User                                              ║" +
                "\n║                               ■  [4]      Remove User                                            ║" +
                "\n║                               ■  [5]      Sort User                                              ║" +
                "\n║                               ■  [6]      Come Back                                              ║" +
                "\n║                               ■  [0]      Exit                                                   ║" +
                "\n║                                                                                                  ║" +
                "\n║                                                                                                  ║" +
                "\n╚══════════════════════════════════════════════════════════════════════════════════════════════════╝" +
                "\n");

    }

    public static void run() {
        UserView userView = new UserView();
        do {
            menuUser();
            try {
                System.out.println("Enter your choice : ");
                int number = Integer.parseInt(sc.nextLine());
                switch (number) {
                    case 1:
                        System.out.println("Add User");
                        userView.addUser();
                        break;
                    case 2:
                        System.out.println("Update User");
                        userView.updateUser();
                        break;
                    case 3:
                        System.out.println("Show Users List");
                        userView.showUser(InputOption.SHOW);
                        break;
                    case 4:
                        System.out.println("Remove Users");
                        userView.Remove();
                        break;
                    case 5:
                        System.out.println("Sort Users");
                        ManagerUserView.runSortUser();
                        break;
                    case 6:
                        System.out.println("Come Back");
                        MenuView.showMainMenu();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Wrong choice ! Try again !!!");
                        run();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong input ! Try again !!!");
                run();
            }
        } while (true);
    }

    public static void menuSortUser() {
        System.out.print("╔══════════════════════════════════════════════════════════════════════════════════════════════════╗" +
                "\n║                                                                                                  ║" +
                "\n║                                          [Menu Sort]                                             ║" +
                "\n║                                                                                                  ║" +
                "\n║                               ■  [1]      Sort Ascending By Name                                 ║" +
                "\n║                               ■  [2]      Sort Descending By Name                                ║" +
                "\n║                               ■  [3]      Sort Ascending By ID                                   ║" +
                "\n║                               ■  [4]      Sort Descending By ID                                  ║" +
                "\n║                               ■  [5]      Come Back                                              ║" +
                "\n║                               ■  [0]      Exit                                                   ║" +
                "\n║                                                                                                  ║" +
                "\n╚══════════════════════════════════════════════════════════════════════════════════════════════════╝" +
                "\n")
        ;
    }

    public static void runSortUser() {
        UserView userView = new UserView();
        do {
            menuSortUser();
            try {
                System.out.println("Enter your choice : ");
                int num = Integer.parseInt(sc.nextLine());
                switch (num) {
                    case 1:
                        System.out.println("Sort Ascending By Name");
//                        hàm sort tăng dần theo tên
                        userView.sortByNameByASC();
                        break;
                    case 2:
                        System.out.println("Sort Descending By Name");
//                        hàm sort giảm dần theo tên
                        userView.sortByNameByDESC();
                        break;
                    case 3:
                        System.out.println("Sort Ascending By ID");
//                        hàm sort tăng dần theo ID
                        userView.sortByIdByASC();
                        break;
                    case 4:
                        System.out.println("Sort Descending By ID");
//                        hàm sort giảm dần theo ID
                        userView.sortByIdByDESC();
                        break;
                    case 5:
                        System.out.println("Come Back");
                        ManagerUserView.run();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Wrong choice ! Try again !!!");
                        runSortUser();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong input ! Try again !!!");
                runSortUser();
            }
        } while (true);
    }
}



