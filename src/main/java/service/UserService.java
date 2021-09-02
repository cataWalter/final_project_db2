package service;

import entity.*;

import javax.persistence.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public User selectUser(User credentials) {
        User newUser = null;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<User> myQuery = entityManager.createNamedQuery("User.byUsernamePassword", User.class);
            myQuery.setParameter(1, credentials.getUsername());
            myQuery.setParameter(2, credentials.getPassword());
            newUser = myQuery.getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            transaction.commit();
            entityManager.close();
            entityManagerFactory.close();
            return null;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return newUser;
    }

    public User selectUser(String username) {
        User newUser = null;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<User> myQuery = entityManager.createNamedQuery("User.byUsername", User.class);
            myQuery.setParameter(1, username);
            newUser = myQuery.getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            transaction.commit();
            entityManager.close();
            entityManagerFactory.close();
            return null;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return newUser;
    }


    public List<User> selectLeaderboard() throws SQLException {
        List<User> users = new ArrayList<>();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<User> myQuery = entityManager.createNamedQuery("User.selectLeaderboard", User.class);
            users.addAll(myQuery.getResultList());

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        entityManager.close();
        entityManagerFactory.close();
        return users;
    }

    public void userInsert(User newUser) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newUser);
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        entityManager.close();
        entityManagerFactory.close();


    }

    public User getUser(int userId) throws SQLException {
        User user;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<User> q = entityManager.createNamedQuery("User.byId", User.class);
            q.setParameter(1, userId);
            user = q.getSingleResult();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return user;
    }


    public List<User> selectUserByAnswer(String productId, int sent) throws Exception {
        List<User> users = new ArrayList<>();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Product> q1 = entityManager.createNamedQuery("Product.byId", Product.class);
            q1.setParameter(1, Integer.parseInt(productId));
            TypedQuery<User> q2 = entityManager.createNamedQuery("User.byAnswer", User.class);
            q2.setParameter(1, q1.getSingleResult());
            q2.setParameter(2, sent);
            users.addAll(q2.getResultList());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return users;
    }

    public void blockUser(User user) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query q1 =
                    entityManager.createQuery("UPDATE User SET blocked = 1 WHERE userId = " + Integer.toString(user.getUserId()));
            q1.executeUpdate();
            Query q2 =
                    entityManager.createQuery("DELETE FROM Answer WHERE userId = " + Integer.toString(user.getUserId()));
            q2.executeUpdate();


            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
    }


}
