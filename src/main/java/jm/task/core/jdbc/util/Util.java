package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASSWORD = "12345";
    public static final String DRIVERCLASS = "org.postgresql.Driver";

    private static SessionFactory sessionFactory;

    private Util() {}

    public static SessionFactory getSessionFactory() throws SQLException{
        if (sessionFactory == null) {
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.driver_class", DRIVERCLASS);
                configuration.setProperty("hibernate.connection.url", URL);
                configuration.setProperty("hibernate.connection.username", USER);
                configuration.setProperty("hibernate.connection.password", PASSWORD);
                configuration.addAnnotatedClass(User.class);
                sessionFactory = configuration.buildSessionFactory();

        }
        return sessionFactory;
    }
}
