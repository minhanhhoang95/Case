package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;
    List<Order> orders = new ArrayList<>();

    public User() {

    }


    public User(long id, String username, String password, String fullname, String phone, String address, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role=role;
    }

    public static User parseUser(String row) {
        User user = new User();
        String[] userInformation = row.split(",");
        user.id =Long.parseLong(userInformation[0]) ;
        user.username = userInformation[1];
        user.password = userInformation[2];
        user.fullName = userInformation[3];
        user.phone = userInformation[4];
        user.address = userInformation[5];
        user.email = userInformation[6];
        user.role = Role.parseRole(userInformation[7]);
        user.createdAt = Instant.parse(userInformation[8]);
        String temp = userInformation[9];
        if (temp != null && !temp.equals("null")){
            user.updatedAt = Instant.parse(temp);
        }

        return user;
    }

    public User(Long id, String username, String password, String fullName, String phone, String address, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                id,
                username,
                password,
                fullName,
                phone,
                email,
                address,
                role,
                createdAt,
                updatedAt
        );
    }
}
