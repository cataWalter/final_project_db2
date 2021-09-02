package service;

import entity.*;

import javax.persistence.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public List<Product> getProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Product> myQuery = entityManager.createNamedQuery("Product.selectAll", Product.class);
            products.addAll(myQuery.getResultList());
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        entityManager.close();
        entityManagerFactory.close();
        return products;
    }



    public Product selectProduct(String productName) {

        Product newProduct = new Product();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Product> myQuery = entityManager.createNamedQuery("Product.byName", Product.class);
            myQuery.setParameter(1, productName);
            newProduct = myQuery.getResultList().get(0);
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        entityManager.close();
        entityManagerFactory.close();
        return newProduct;
    }



    public void productInsert(Product newProduct) throws SQLException {           //INSERT INTO (Da modificare in base all'applicazione)
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newProduct);
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
    }

    public void deleteDate(String toDelete) throws SQLException {           //INSERT INTO (Da modificare in base all'applicazione)
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query query = entityManager.createQuery("DELETE FROM Answer WHERE " +
                    "productId" +
                    " = " +
                    "(SELECT productId FROM Product WHERE day = '" + toDelete + "')");
            query.executeUpdate();
            Query query1 = entityManager.createQuery("DELETE FROM Product WHERE day = '" + toDelete + "'");
            query1.executeUpdate();
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
    }




    public Product selectProductOfTheDay() throws SQLException {
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDate = LocalDate.now();
        Product x;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Product> myQuery = entityManager.createNamedQuery("Product.byDay", Product.class);
            myQuery.setParameter(1, dtf1.format(localDate));
            x = myQuery.getSingleResult();
            transaction.commit();
        }
        catch (NoResultException e){
            transaction.commit();
            entityManager.close();
            entityManagerFactory.close();
            return null;
        }

        finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return x;
    }

}
