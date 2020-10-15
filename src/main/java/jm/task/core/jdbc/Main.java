package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Util.getDbConnection();
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();

        daoHibernate.createUsersTable();
        daoHibernate.saveUser("PAlex", "PIvanov", (byte) 14);
        daoHibernate.saveUser("Mamix", "PIvanov", (byte) 14);
        //daoHibernate.getAllUsers();
        //daoHibernate.cleanUsersTable();
        //daoHibernate.removeUserById(1);
        //daoHibernate.dropUsersTable();
        //daoJDBC.saveUser("Первый", "Игрок", (byte) 14);
        //daoJDBC.saveUser("Второй", "Игрок", (byte) 24);
        //daoJDBC.saveUser("Третий", "Игрок", (byte) 34);
        //daoJDBC.saveUser("Четвертый", "Игрок", (byte) 44);
        //daoJDBC.getAllUsers();
        daoJDBC.cleanUsersTable();
        //daoJDBC.dropUsersTable();

        //Util.getSessionFactory();
    }




}
