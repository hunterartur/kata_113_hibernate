package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String QUERY_CREATE_USER_TABLE = "create table if not exists katadb_1_1_3.users (id bigint not null auto_increment, name varchar(50) not null, lastname varchar(50) not null, age tinyint, primary key(id));";
    private static final String QUERY_DROP_USER_TABLE = "drop table if exists katadb_1_1_3.users";
    private static final String QUERY_SAVE_USER = "insert into katadb_1_1_3.users (name, lastname, age) value(?, ?, ?);";
    private static final String QUERY_REMOVE_USER_BY_ID = "delete from katadb_1_1_3.users where id=?";
    private static final String QUERY_GET_ALL_USERS = "select * from katadb_1_1_3.users";
    private static final String QUERY_CLEAN_USERS_TABLE = "delete from katadb_1_1_3.users";
    private Connection connection = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate(QUERY_CREATE_USER_TABLE);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void dropUsersTable() {

        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate(QUERY_DROP_USER_TABLE);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void cleanUsersTable() {

        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            connection.createStatement().executeUpdate(QUERY_CLEAN_USERS_TABLE);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SAVE_USER);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {

        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_USER_BY_ID);
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        connection = Util.getConnection();
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(QUERY_GET_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
