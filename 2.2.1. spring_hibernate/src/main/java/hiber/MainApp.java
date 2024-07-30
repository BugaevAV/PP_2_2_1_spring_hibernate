package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car1 = new Car("BMW", 5);
      carService.addCar(car1);
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", car1));
      Car car2 = new Car("Mercedes", 213);
      carService.addCar(car2);
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));
      Car car3 = new Car("Audi", 8);
      carService.addCar(car3);
      userService.add(new User("User7", "Lastname7", "user7@mail.ru", car3));
      Car car4 = new Car("Газ", 66);
      carService.addCar(car4);
      userService.add(new User("User8", "Lastname8", "user8@mail.ru", car4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      User userWithBMW = carService.getUser("BMW", 5);
      User userWithGAZ = carService.getUser("Газ", 66);
      User userWithAudi = carService.getUser("Audi", 8);
      for (User user : new User[] {userWithBMW, userWithGAZ, userWithAudi}) {
         System.out.println("Владелец " + user.getCar() + " : " + user);
      }

      context.close();
   }
}
