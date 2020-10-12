package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        return daoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.cleanUsersTable();
    }
}
