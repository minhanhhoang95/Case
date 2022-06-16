package views;

import java.util.Scanner;



public class MenuView {
    public static void showMainMenu() {
        Scanner sc = new Scanner(System.in);
//        public static void exit() {
//            ThreadExit threadExit = new ThreadExit();
//            Thread thread1 = new Thread(threadExit);
//            thread1.start();
//            try{
//                thread1.join();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
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
        while (choice!=0){
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
                case 4:
//                    MenuView.exit();
                    break;
                default:
                    System.out.println("Wrong Input! Please enter a number !!! ");
                    showMainMenu();
            }
        }
    }







}
