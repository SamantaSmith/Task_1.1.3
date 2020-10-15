package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Const;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            String create = "CREATE TABLE IF NOT EXISTS users ("
                    + "ID BIGINT(30) NOT NULL AUTO_INCREMENT,"
                    + "NAME VARCHAR(255) NOT NULL,"
                    + "LASTNAME VARCHAR(45) NOT NULL,"
                    + "AGE TINYINT(5) NOT NULL,"
                    + "PRIMARY KEY (ID))";

            SQLQuery query = session.createSQLQuery(create);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }


    }

    @Override
    public void dropUsersTable() {

        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            String drop = "DROP TABLE IF EXISTS users";
            SQLQuery query = session.createSQLQuery(drop);
            query.executeUpdate();
            tx.commit();
        } catch(Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }


    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = null;
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            userList = session.createCriteria(User.class).list();
            System.out.println(userList);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            String sql = "DELETE FROM User";
            Query query = session.createQuery(sql);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
