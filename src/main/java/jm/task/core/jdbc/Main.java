package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Util.getDbConnection();
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();


        daoJDBC.createUsersTable();
        daoJDBC.saveUser("Первый", "Игрок", (byte) 14);
        daoJDBC.saveUser("Второй", "Игрок", (byte) 24);
        daoJDBC.saveUser("Третий", "Игрок", (byte) 34);
        daoJDBC.saveUser("Четвертый", "Игрок", (byte) 44);
        daoJDBC.getAllUsers();
        daoJDBC.cleanUsersTable();
        daoJDBC.dropUsersTable();
    }




}
