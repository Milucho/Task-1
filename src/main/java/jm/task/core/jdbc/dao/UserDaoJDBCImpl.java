package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD); Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (Id SERIAL PRIMARY KEY, name VARCHAR (255), lastname VARCHAR(255) , age SMALLINT)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT name, lastname, age FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getByte("age"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


        public void cleanUsersTable () {

            try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate("TRUNCATE TABLE users");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

