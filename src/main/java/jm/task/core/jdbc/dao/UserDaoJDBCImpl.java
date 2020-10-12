package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Const;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String create = "CREATE TABLE IF NOT EXISTS users ("
                + "ID BIGINT(30) NOT NULL AUTO_INCREMENT,"
                + "NAME VARCHAR(255) NOT NULL,"
                + "LASTNAME VARCHAR(45) NOT NULL,"
                + "AGE TINYINT(5) NOT NULL,"
                + "PRIMARY KEY (ID))";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Util.getDbConnection().prepareStatement(create);
            preparedStatement.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String drop = "DROP TABLE IF EXISTS users";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Util.getDbConnection().prepareStatement(drop);
            preparedStatement.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age)  {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," + Const.USERS_LASTNAME + ","
                + Const.USERS_AGE + ")" + "VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = Util.getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {

        PreparedStatement preparedStatement = null;
        String remove = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_ID + "=?";

        try {
            preparedStatement = Util.getDbConnection().prepareStatement(remove);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String getAll = "SELECT * FROM " + Const.USER_TABLE;
        Statement statement = null;

        try {
            statement = Util.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getAll);

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(Const.USERS_ID));
                user.setName(resultSet.getString(Const.USERS_NAME));
                user.setLastName(resultSet.getString(Const.USERS_LASTNAME));
                user.setAge(resultSet.getByte(Const.USERS_AGE));

                users.add(user);
                System.out.println(user.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {


        PreparedStatement preparedStatement = null;
        String remove = "DELETE FROM " + Const.USER_TABLE;

        try {
            preparedStatement = Util.getDbConnection().prepareStatement(remove);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
