package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user,String model, int series);
   List<User> listUsers();
   User findUsers(String model, int series);
}
