package views;

import model.Role;
import model.User;
import service.IUserService;
import service.UserService;
import utils.AppUtils;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public AdminView() {
        userService = UserService.getInstance();
    }

//    public Role set(String username, String password) {
//        List<User> users = userService.findAll();
//        for (User user : users) {
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)
//                    && user.getRole().equals(Role.ADMIN)) {
//                return Role.ADMIN;
//            }
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)
//                    && user.getRole().equals(Role.USER)) {
//                return Role.USER;
//            }
//
//        }
//        return null;
//    }

    public void Login() {
        boolean isRetry = false;
        System.out.println(" ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println("   ✦                                                 ✦");
        System.out.println(" ✦                    Bar DEBAY                    ✦");
        System.out.println("   ✦                                                 ✦");
        System.out.println("  ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println("   ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ LOGIN ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println(" ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        do {
            System.out.println("Username");
            String username = AppUtils.retryString("Username");
            System.out.println("Password");
            String password = AppUtils.retryString("Password");
            User user = userService.adminLogin(username, password);

            if (user == null) {
                System.out.println("This account is Invalid");
                isRetry = isRetry();
            } else if (user.getRole() == Role.ADMIN) {
                System.out.println("Logged in successfully");
                System.out.println("WELCOME TO BAR DEBAY");
                MenuView.showMainMenu();
            } else if (user.getRole() == Role.USER) {
                System.out.println("Logged in successfully");
                System.out.println("WELCOME TO BAR DEBAY");
                MenuUser.run();
            }

//                if (set(username,password).equals(Role.ADMIN)){
//                    System.out.println("Logged in successfully");
//                    System.out.println("WELCOME TO BAR DEBAY");
//                    MenuView.showMainMenu();
//                    isRetry = false;
//                }
//                if (set(username,password).equals(Role.USER)){
//                    System.out.println("Logged in successfully");
//                    System.out.println("WELCOME TO BAR DEBAY");
//                    MenuUser.run();
//                    isRetry = false;
//                }
//            User user = null ;
//            switch (role){
//                case USER:
//                    user=userService.UserLogin(username,password);
//                    break;
//                case ADMIN:
//                    user=userService.adminLogin(username,password);
//                    break;
//            }
//
//            if (userService.adminLogin(username, password) == null) {
//                System.out.println("This account is Invalid");
//                isRetry = isRetry();
//            }
//            else {
//                System.out.println("Logged in successfully");
//                System.out.println("WELCOME TO BAR DEBAY");
//                isRetry = false;
//            }
        } while (isRetry);
    }

    private boolean isRetry() {
        do {
            try {
                System.out.println("✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
                System.out.println("  ✦                                        ✦ ");
                System.out.println("✦      1.Press 'y' to login again        ✦ ");
                System.out.println("  ✦    2.Press 'n' to exit the program     ✦");
                System.out.println("✦                                        ✦ ");
                System.out.println("✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
                System.out.print(" ⭆ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "n":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Choose the wrong function! Please choose again");
                        break;
                }

            } catch (Exception ex) {
                System.out.println("Syntax error! please enter again");
                ex.printStackTrace();
            }
        } while (true);
    }
}
