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

    public void createUsersTable() throws SQLException {

        String create = "CREATE TABLE IF NOT EXISTS users ("
                + "ID BIGINT(30) NOT NULL AUTO_INCREMENT,"
                + "NAME VARCHAR(255) NOT NULL,"
                + "LASTNAME VARCHAR(45) NOT NULL,"
                + "AGE TINYINT(5) NOT NULL,"
                + "PRIMARY KEY (ID))";

        Connection dbConnection = Util.getDbConnection();
        try (PreparedStatement preparedStatement1 = Util.getDbConnection().prepareStatement(create)) {
            dbConnection.setAutoCommit(false);
            preparedStatement1.executeUpdate(create);
            dbConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        }
        finally {
            dbConnection.setAutoCommit(true);
        }
    }

    public void dropUsersTable() throws SQLException {

        String drop = "DROP TABLE IF EXISTS users";
        PreparedStatement preparedStatement = null;
        Connection dbConnection = Util.getDbConnection();
        try(PreparedStatement preparedStatement1 = Util.getDbConnection().prepareStatement(drop)) {
            dbConnection.setAutoCommit(false);
            preparedStatement1.executeUpdate(drop);
            dbConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        }
        finally {
            dbConnection.setAutoCommit(true);
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," + Const.USERS_LASTNAME + ","
                + Const.USERS_AGE + ")" + "VALUES(?,?,?)";
        Connection dbConnection = Util.getDbConnection();
        try(PreparedStatement preparedStatement1 = Util.getDbConnection().prepareStatement(insert)) {

            dbConnection.setAutoCommit(false);
            preparedStatement1.setString(1, name);
            preparedStatement1.setString(2, lastName);
            preparedStatement1.setByte(3, age);
            preparedStatement1.executeUpdate();

            dbConnection.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");


        } catch (SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        }
        finally {
            dbConnection.setAutoCommit(true);
        }
    }

    public void removeUserById(long id) throws SQLException {


        String remove = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_ID + "=?";
        Connection dbConnection = Util.getDbConnection();

        try(PreparedStatement preparedStatement = Util.getDbConnection().prepareStatement(remove)) {
            dbConnection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            dbConnection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        }
        finally {
            dbConnection.setAutoCommit(true);
        }
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> users = new ArrayList<>();
        String getAll = "SELECT * FROM " + Const.USER_TABLE;
        Connection dbConnection = Util.getDbConnection();

        try(Statement statement = Util.getDbConnection().createStatement())  {
            dbConnection.setAutoCommit(false);
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
            dbConnection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.setAutoCommit(true);
        }

        return users;
    }

    public void cleanUsersTable() throws SQLException {



        String remove = "DELETE FROM " + Const.USER_TABLE;
        Connection dbConnection = Util.getDbConnection();

        try(PreparedStatement preparedStatement = Util.getDbConnection().prepareStatement(remove)) {
            dbConnection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            dbConnection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        }
        finally {
            dbConnection.setAutoCommit(true);
        }
    }
}
