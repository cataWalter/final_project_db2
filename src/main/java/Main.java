import entity.User;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<User> userByDeptQuery = entityManager.createNamedQuery("UserByDepartment", User.class);
            userByDeptQuery.setParameter(1, "firstDep");
                System.out.println(userByDeptQuery.getResultList());
            transaction.commit();

        }
        finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}

