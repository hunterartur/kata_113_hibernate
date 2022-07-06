package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Artur", "Ishmaev", (byte) 28);
        userService.saveUser("Yulduz", "Nasibullina", (byte) 28);
        userService.saveUser("Tyson", "GoodBoy", (byte) 5);
        userService.saveUser("Zulfiya", "Karimova", (byte) 48);
        userService.saveUser("Vadim", "Vasilev", (byte) 20);

//        userService.removeUserById(1);

        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
