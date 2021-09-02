package service;

import entity.*;

import javax.persistence.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {



    public void questionInsert(Question newQuestion) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newQuestion);
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        entityManager.close();
        entityManagerFactory.close();

    }




    public List<Question> getQuestionByProductId(String productId) throws SQLException {
        List<Question> questions = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Product> q1 = entityManager.createNamedQuery("Product.byId", Product.class);
            q1.setParameter(1, Integer.parseInt(productId));
            Product newProduct = q1.getSingleResult();
            TypedQuery<Question> q2 = entityManager.createNamedQuery("Question.byProduct", Question.class);
            q2.setParameter(1, newProduct);
            questions.addAll(q2.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return questions;
    }


    public List<Question> getDefaultQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Question> q = entityManager.createNamedQuery("Question.byDefault", Question.class);
            q.setParameter(1, 0);
            questions.addAll(q.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return questions;
    }

}
