package views;

import model.Role;
import model.User;
import service.IUserService;
import service.UserService;
import utils.AppUtils;
import utils.InstantUtils;
import utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final IUserService userService;
    private final Scanner sc = new Scanner(System.in);

    public UserView() {
        userService = UserService.getInstance();
    }

    public void showUser(InputOption inputOption) {
        System.out.println("════════════════════════════════════════════════ User List ════════════════════════════════════════════════");
        System.out.printf("%-15s %-20s %-15s %-20s %-20s %-10s %-20s %-20s \n", "Id", "Name", "Phone", "Address", "Email", "User", "Date Created", "Date Edit");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.printf("%-15s %-20s %-15s %-20s %-20s %-10s %-20s %-20s \n",
                    user.getId(),
                    user.getFullName(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getEmail(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getCreatedAt()),
                    user.getUpdatedAt() == null ? "" : InstantUtils.instantToString(user.getUpdatedAt())
            );
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
        if (inputOption == InputOption.SHOW) {
            AppUtils.isRetry(InputOption.SHOW);
        }
    }

    public void addUser() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String username = inputUsername();
                String password = inputPassword();
                String fullName = inputFullName(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String email = inputEmail();
                String address = inputAddress(InputOption.ADD);

                User user = new User(id, username, password, fullName, phone, email, address, Role.USER);
                setRole(user);
                userService.add(user);
                System.out.println("Added success!");
            } catch (Exception e) {
                System.out.println("Syntax error. Please re-enter!");
            }

        } while (AppUtils.isRetry(InputOption.ADD));
    }

    public void updateUser() {
        boolean isRetry = false;
        do {
            try {
                showUser(InputOption.UPDATE);
                int id = inputId(InputOption.UPDATE);
                System.out.print("╔═══════════════════════════════════════════════════════════════════════════════╗" +
                        "\n║                                                                               ║" +
                        "\n║                                   [Edit User]                                 ║" +
                        "\n║                                                                               ║" +
                        "\n║                               ■  [1]   Edit Name                              ║" +
                        "\n║                               ■  [2]   Edit Phone                             ║" +
                        "\n║                               ■  [3]   Edit Address                           ║" +
                        "\n║                               ■  [4]   Come Back                              ║" +
                        "\n║                               ■  [0]   Exit                                   ║" +
                        "\n║                                                                               ║" +
                        "\n╚═══════════════════════════════════════════════════════════════════════════════╝" +
                        "\n");
                System.out.print(" ⭆ ");

                int option = AppUtils.retryChoose(0, 4);
                User newUser = new User();
                newUser.setId((long) id);
                switch (option) {
                    case 1:
                        String Name = inputFullName(InputOption.UPDATE);
                        newUser.setFullName(Name);
                        userService.update(newUser);
                        System.out.println("You have successfully renamed!");
                        break;
                    case 2:
                        String Phone = inputPhone(InputOption.UPDATE);
                        newUser.setPhone(Phone);
                        userService.update(newUser);
                        System.out.println("You have successfully changed your phone number!");
                        break;
                    case 3:
                        String Address = inputAddress(InputOption.UPDATE);
                        newUser.setAddress(Address);
                        userService.update(newUser);
                        System.out.println("You have successfully redeemed!");
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                }
                isRetry = option != 4 && AppUtils.isRetry(InputOption.UPDATE);
            } catch (Exception e) {
                System.out.println("Syntax error! please re-enter");
            }
        } while (isRetry);
    }

    public void Remove() {
        showUser(InputOption.DELETE);
        int id;
        while (!UserService.getInstance().existById(id = inputId(InputOption.DELETE))) {
            System.out.println("Can't find User item to remove");
            System.out.println("Press 'y' to add more \t|\t 'q' to come back \t|\t 't' to exit the program");
            System.out.print(" ⭆ ");
            String option = sc.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Choose the wrong function! Please choose again");
                    break;
            }
        }
        System.out.println("   ❄ ❄ ❄ ❄ REMOVE COMFIRM ❄ ❄ ❄");
        System.out.println("  ❄      1. Press 1 to delete     ❄");
        System.out.println(" ❄       2. Press 2 to go back     ❄");
        System.out.println("❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            userService.deleteById(id);
            System.out.println("User Remove Successfully!");
            AppUtils.isRetry(InputOption.DELETE);
        }
    }


    public void setRole(User user) {
        System.out.print("╔═══════════════════════════════════════════════════════════════════════════════╗" +
                "\n║                                                                               ║" +
                "\n║                                    [SET ROLE]                                 ║" +
                "\n║                                                                               ║" +
                "\n║                               ■  [1]      USER                                ║" +
                "\n║                               ■  [2]      ADMIN                               ║" +
                "\n║                                                                               ║" +
                "\n╚═══════════════════════════════════════════════════════════════════════════════╝" +
                "\n")
        ;
        System.out.println("Enter to select a Role");
        System.out.print(" ⭆ ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
            default:
                System.out.println("Syntax error! Please re-enter");
                setRole(user);
        }

    }

    private int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Enter Id : ");
                break;
            case UPDATE:
                System.out.println("Enter the Id you want to edit");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = userService.existById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("This id already exists. Please enter another id!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("id not found! Please re-enter");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }

    private String inputUsername() {
        System.out.println("Enter Username (do not include spaces, special characters)");
        String username;
        do {
            if (!ValidateUtils.isUsernameValid(username = AppUtils.retryString("username"))) {
                System.out.println(username + " Yours is malformed! Please check and re-enter");
                continue;
            }
            if (userService.existsByUsername(username)) {
                System.out.println("This username already exists. Please re-enter");
                continue;
            }
            break;
        } while (true);
        return username;
    }

    private String inputFullName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Enter your FullName (eg: Hoang Anh Minh)");
                break;
            case UPDATE:
                System.out.println("Enter the name you want to edit");
                break;
        }
        System.out.print(" ⭆ ");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = sc.nextLine())) {
            System.out.println("Name" + fullName + " Malformed." + " Please re-enter!" + "(Name must be capitalized and not accented)");
            System.out.println("Enter your name (eg: Hoang Anh Minh)");
            System.out.print(" ⭆ ");
        }
        return fullName;
    }

    private String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Enter phone number (eg: 0394249825)");
                break;
            case UPDATE:
                System.out.println("Enter the phone number you want to change");
                break;
        }
        System.out.print(" ⭆ ");
        String phone;
        do {
            phone = sc.nextLine();
            if (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Number" + phone + "yours is not correct. Please re-enter!" + "(Phone number consists of 10 numbers and starts with 0)");
                System.out.println("Enter phone number (eg: 0394249825)");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByPhone(phone)) {
                System.out.println("This number already exists! Please re-enter");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);
        return phone;
    }

    private String inputEmail() {
        System.out.println("Enter email (eg: minh@gmail.com)");
        System.out.print(" ⭆ ");
        String email;
        do {
            if (!ValidateUtils.isEmailValid(email = sc.nextLine())) {
                System.out.println("Email" + email + "your format is wrong! Please check and re-enter");
                System.out.println("Enter email (eg: minh@gmail.com)");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByEmail(email)) {
                System.out.println("Email" + email + "Yours already exists! please check again.");
                System.out.println("Enter email (eg: minh@gmail.com)");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);
        return email;
    }

    private String inputPassword() {
        System.out.println("Enter password (password > 8 characters)");
        System.out.print(" ⭆ ");
        String Password;
        while (!ValidateUtils.isPasswordValid(Password = sc.nextLine())) {
            System.out.println("Weak password! Please re-enter");
            System.out.print(" ⭆ ");
        }
        return Password;
    }

    private String inputAddress(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Enter an address (eg: Hue)");
                break;
            case UPDATE:
                System.out.println("Enter the address you want to change");
                break;
        }
        System.out.print(" ⭆ ");
        return sc.nextLine();
    }

    public void showUserSort(InputOption inputOption, List<User> users) {
        System.out.println("════════════════════════════════════════════════ User List ════════════════════════════════════════════════");
        System.out.printf("%-15s %-20s %-15s %-20s %-20s %-10s %-20s %-20s \n", "Id", "Name", "Phone", "Address", "Email", "User", "Date Created", "Date Edit");
        for (User user : users) {
            System.out.printf("%-15s %-20s %-15s %-20s %-20s %-10s %-20s %-20s \n",
                    user.getId(),
                    user.getFullName(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getEmail(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getCreatedAt()),
                    user.getUpdatedAt() == null ? "" : InstantUtils.instantToString(user.getUpdatedAt())
            );
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
        if (inputOption == InputOption.SHOW) {
            AppUtils.isRetry(InputOption.SHOW);
        }
    }
    public void sortByNameByASC() {
        showUserSort(InputOption.SHOW, userService.SortByNameASC());
    }
    public void sortByNameByDESC(){
        showUserSort(InputOption.SHOW,userService.SortByNameDESC());
    }
    public void sortByIdByASC(){
        showUserSort(InputOption.SHOW,userService.SortByIDASC());
    }
    public void sortByIdByDESC(){
        showUserSort(InputOption.SHOW,userService.SortByIDDESC());
    }

}
