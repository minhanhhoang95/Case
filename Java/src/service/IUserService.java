package service;

import model.User;

import java.util.List;

public interface IUserService {
    List<User> getUser();

    User loginAdmin(String username, String password);

    void add(User newUser);

    void update(User newUser);

    boolean exist(long id);

    boolean checkDuplicateEmail(String email);

    boolean checkDuplicatePhone(String phone);

    boolean checkDuplicateUserName(String userName);

    boolean checkDuplicateId(long id);

    User getUserById(long id);

    boolean existById(long id);
}
