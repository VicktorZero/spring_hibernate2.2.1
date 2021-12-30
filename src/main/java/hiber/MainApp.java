package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Ivan", "Ivanov", "ivanov@mail.ru"),"BMV",3);
      userService.add(new User("Petr", "Sidorov", "sidorov@mail.ru"),"Lada", 7);
      userService.add(new User("Mariya", "Semenova", "semenova@mail.ru"),"Reno", 4);
      userService.add(new User("Elena", "Dugina", "dugina@mail.ru"),"Toyta", 5);

    User users1 = userService.findUsers("BMV",3);
    Car car = users1.getUserCars();
      System.out.println(car.getModel()+ ":" + car.getSeries());
   User users2 = userService.findUsers("Lada",7);
       System.out.println(users2.getUserCars());

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
          car = user.getUserCars();
         System.out.println("UsersCars_id" + ":" + car.getModel()+":" + car.getSeries());
         System.out.println();
      }

      context.close();
   }
}
