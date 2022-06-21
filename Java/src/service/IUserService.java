package service;

import model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User adminLogin(String username, String password);

//    User UserLogin(String username, String password);
    void add(User newUser);

    void update(User newUser);
     void deleteById(long id);
    boolean existById(long id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByUsername(String userName);

    User findById(long id);
    List<User> SortByNameASC();


    List<User> SortByNameDESC();

    List<User> SortByIDASC();

    List<User> SortByIDDESC();
}
