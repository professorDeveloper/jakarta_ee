package org.azamov.learnjakarta.task7_1.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.azamov.learnjakarta.task7_1.helper.EntityManagerUtil;
import org.azamov.learnjakarta.task7_1.model.User;

import java.util.List;

public class AuthService {

    public Integer login(String username, String password) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return em.createQuery(
                            "SELECT u.id FROM User u WHERE u.username = :username AND u.password = :password",
                            Integer.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User register(User user) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void delete(int id) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM User u WHERE u.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return em.createQuery("SELECT u FROM User u ORDER BY u.id DESC", User.class)
                    .getResultList();
        }
    }
}
