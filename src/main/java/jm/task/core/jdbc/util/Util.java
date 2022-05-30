package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    // реализуйте настройку соеденения с БД
    private static final String HOSTNAME = "localhost";
    private static final String PORT = "3306";
    private static final String NAME_DB = "katadb_1_1_3";
    private static final String USERNAME = "user1";
    private static final String PASSWORD = "kata_113";

    public static Connection getConnection() {
        Connection connection = null;
        String connectionLine = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + NAME_DB;
        try {
            connection = DriverManager.getConnection(connectionLine, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        try {
            properties.load(Util.class.getResourceAsStream("/hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
