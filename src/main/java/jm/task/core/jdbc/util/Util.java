package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    // реализуйте настройку соеденения с БД
    private static final String USERNAME = "user1";
    private static final String PASSWORD = "kata_113";
    private static final String URL = "jdbc:mysql://localhost:3306/katadb_1_1_3";
    private static  SessionFactory sessionFactory = null;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/katadb_1_1_3?useSSL=false");
            properties.setProperty("hibernate.connection.username", "user1");
            properties.setProperty("hibernate.connection.password", "kata_113");
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("hibernate.connection.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("hibernate.connection.autocommit", "true");
            properties.setProperty("current_session_context_class", "thread");
            properties.setProperty("hibernate.show_sql", "true");
            sessionFactory =  new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
