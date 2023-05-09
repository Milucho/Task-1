package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService table = new UserServiceImpl();
        table.createUsersTable();
        table.saveUser("Milena", "Bespalaya", (byte) 22);
        table.saveUser("Ivan", "Ivanov", (byte) 43);
        table.saveUser("Krosh", "Smesharikov", (byte) 8);
        table.saveUser("Kick", "Buttowski", (byte) 15);
        List<User> list = table.getAllUsers();
        for (User i: list){
            System.out.println(i.toString());
        }

        table.cleanUsersTable();
        table.dropUsersTable();


    }
}
