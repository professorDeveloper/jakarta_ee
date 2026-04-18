package org.azamov.learnjakarta.task7_1.helper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("orm_example");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
