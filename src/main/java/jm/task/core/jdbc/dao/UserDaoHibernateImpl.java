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

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();

        String create = "CREATE TABLE IF NOT EXISTS users ("
                + "ID BIGINT(30) NOT NULL AUTO_INCREMENT,"
                + "NAME VARCHAR(255) NOT NULL,"
                + "LASTNAME VARCHAR(45) NOT NULL,"
                + "AGE TINYINT(5) NOT NULL,"
                + "PRIMARY KEY (ID))";

        Transaction tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery(create);
        query.executeUpdate();

    }

    @Override
    public void dropUsersTable() {

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        String drop = "DROP TABLE IF EXISTS users";
        Transaction tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery(drop);
        query.executeUpdate();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        session.getTransaction().commit();



    }

    @Override
    public void removeUserById(long id) {

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User();
        user.setId(id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> userList = session.createCriteria(User.class).list();
        System.out.println(userList);
        return userList;

    }

    @Override
    public void cleanUsersTable() {

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "DELETE FROM User";
        Query query = session.createQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();

    }
}
