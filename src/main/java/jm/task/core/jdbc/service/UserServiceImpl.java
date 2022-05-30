package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

//    private UserDao getDao = new UserDaoJDBCImpl();
    private UserDao getDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        getDao.createUsersTable();
    }

    public void dropUsersTable() {
        getDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        getDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        getDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return getDao.getAllUsers();
    }

    public void cleanUsersTable() {
        getDao.cleanUsersTable();
    }
}
