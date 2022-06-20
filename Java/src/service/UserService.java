package service;

import model.Product;
import model.Role;
import model.User;
import utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class UserService implements IUserService {
    public final static String PATH = "data/user.csv";
    private static UserService instance;

    private UserService() {

    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.readData(PATH);
        for (String record : records) {
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public User adminLogin(String username, String password) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)
//                    && user.getRole().equals(Role.ADMIN)) {
//                return user;
//            }
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)
//                    && user.getRole().equals(Role.USER)) {
//                return user;
//            }

        }
        return null;
    }

//    @Override
//    public User UserLogin(String username, String password) {
//        List<User> users = findAll();
//        for (User user : users) {
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)
//                    && user.getRole().equals(Role.USER)) {
//                return user;
//            }
//
//        }
//        return null;
//    }


    @Override
    public void add(User newUser) {
        newUser.setCreatedAt(Instant.now());
        List<User> users = findAll();
        users.add(newUser);
        CSVUtils.writeData(PATH, users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId() == newUser.getId()) {
                String fullName = newUser.getFullName();
                if (fullName != null && !fullName.isEmpty()) {
                    user.setFullName(fullName);
                }
                String phone = newUser.getPhone();
                if (phone != null && !phone.isEmpty()) {
                    user.setPhone(phone);
                }
                String address = newUser.getAddress();
                if (address != null && !address.isEmpty()) {
                    user.setAddress(address);
                }
                user.setUpdatedAt(Instant.now());
                CSVUtils.writeData(PATH, users);
                break;
            }
        }
    }

    @Override
    public void deleteById(int id) {
        List<User> users = findAll();

        //class vo danh
        users.removeIf(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getId() == id;
            }
        });
        CSVUtils.writeData(PATH, users);
    }

    @Override
    public boolean existById(int id) {
        return findById(id) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByPhone(String phone) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByUsername(String userName) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findById(int id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public List<User> SortByNameASC() {
        List<User> users = new ArrayList<>(findAll());
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        return users;
    }


    public List<User> SortByNameDESC() {
        List<User> users = new ArrayList<>(findAll());
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getFullName().compareTo(o1.getFullName());
            }
        });
        return users;
    }

    public List<User> SortByIDASC() {
        List<User> users = new ArrayList<>(findAll());
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                double result = o1.getId() - o2.getId();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });

        return users;
    }

    public List<User> SortByIDDESC() {
        List<User> users = new ArrayList<>(findAll());
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                double result = o2.getId() - o1.getId();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });

        return users;
    }
}
