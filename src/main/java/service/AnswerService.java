package service;

import entity.*;

import javax.persistence.*;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class AnswerService {


    public List<Answer> getAnswers(String userId, String productId) throws SQLException {

        List<Answer> answers = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<User> myQuery1 = entityManager.createNamedQuery("User.byId", User.class);
            myQuery1.setParameter(1, Integer.parseInt(userId));
            TypedQuery<Product> myQuery2 = entityManager.createNamedQuery("Product.byId", Product.class);
            myQuery2.setParameter(1, Integer.parseInt(productId));
            TypedQuery<Answer> myQuery3 = entityManager.createNamedQuery("Answer.byUserProduct", Answer.class);
            myQuery3.setParameter(1, myQuery1.getResultList());
            myQuery3.setParameter(2, myQuery2.getResultList());
            answers.addAll(myQuery3.getResultList());


            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return answers;
    }


    public List<Answer> getSentAnswers(String productId) throws SQLException {

        List<Answer> answers = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<Product> myQuery2 = entityManager.createNamedQuery("Product.byId", Product.class);
            myQuery2.setParameter(1, Integer.parseInt(productId));
            TypedQuery<Answer> myQuery3 = entityManager.createNamedQuery("Answer.byProductSent", Answer.class);
            myQuery3.setParameter(1, myQuery2.getResultList());
            answers.addAll(myQuery3.getResultList());


            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
        return answers;
    }


    public void addAnswer(int userId, int productId, int questionId, String answerText) {
        if (!answerText.isEmpty()) {
            UserService userService = new UserService();
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                TypedQuery<User> q1 = entityManager.createNamedQuery("User.byId", User.class);
                q1.setParameter(1, userId);
                TypedQuery<Product> q2 = entityManager.createNamedQuery("Product.byId", Product.class);
                q2.setParameter(1, productId);
                TypedQuery<Question> q3 = entityManager.createNamedQuery("Question.byId", Question.class);
                q3.setParameter(1, questionId);
                TypedQuery<Explicitword> q4 = entityManager.createNamedQuery("Explicitword.selectAll",
                        Explicitword.class);
                List<Explicitword> explicitwords = q4.getResultList();
                for (Explicitword x : explicitwords) {
                    if (answerText.contains(x.getWord())) {
                        userService.blockUser(q1.getSingleResult());
                        transaction.commit();
                        entityManager.close();
                        entityManagerFactory.close();
                        return;
                    }
                }
                Answer newAnswer = new Answer();
                User user = q1.getSingleResult();
                if (user.getBlocked() == 0) {
                    newAnswer.setAnswerText(answerText);
                    newAnswer.setSuccessfullySent(0);
                    newAnswer.setUserByUserId(user);
                    newAnswer.setProductByProductId(q2.getSingleResult());
                    newAnswer.setQuestionByQuestionId(q3.getSingleResult());
                    entityManager.persist(newAnswer);
                }
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


    public void setSuccessfullySent(int userId, String productId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE Answer SET successfullySent = 1 WHERE userId = " + Integer.toString(userId) + "AND productId = " + productId);
            query.executeUpdate();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
    }

    public boolean alreadyAnswered(int productId, int userId) throws SQLException {
        if (getAnswers(Integer.toString(userId), Integer.toString(productId)).isEmpty())
            return false;
        else
            return true;
    }

    public void delete(String userId, String productId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query query = entityManager.createQuery("DELETE FROM Answer WHERE userId = " + userId +
                    " AND " +
                    "productId" +
                    " = " + productId);
            query.executeUpdate();
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        entityManager.close();
        entityManagerFactory.close();
    }

    public void deleteDefault(String userId, String productId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query query = entityManager.createQuery("DELETE FROM Answer WHERE userId = " + userId +
                    " AND " +
                    "productId" +
                    " = " + productId +
                    " AND questionId IN (SELECT questionId FROM Question WHERE marketingQuestion = 0)");
            query.executeUpdate();
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
