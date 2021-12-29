package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user, String model,int series);
    List<User> listUsers();
    User findUsers(String model, int series);
}
