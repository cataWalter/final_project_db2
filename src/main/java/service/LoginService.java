package service;

import entity.*;

import javax.persistence.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LoginService {



    public void addLogin(String username) throws Exception {
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();

        Login newLogin = new Login();

        newLogin.setLoginDate(dtf1.format(localDate));
        newLogin.setLoginTime(dtf2.format(localTime));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<User> myQuery = entityManager.createNamedQuery("User.byUsername", User.class);
            myQuery.setParameter(1, username);
            newLogin.setUserByUserId(myQuery.getSingleResult());
            entityManager.persist(newLogin);
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
