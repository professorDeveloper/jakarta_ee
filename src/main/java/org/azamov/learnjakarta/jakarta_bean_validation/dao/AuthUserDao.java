package org.azamov.learnjakarta.jakarta_bean_validation.dao;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser;

import java.util.Optional;

@Slf4j
public class AuthUserDao extends BaseDao<AuthUser, String> {

    public Optional<AuthUser> findByToken(String token) {
        try {
            begin();
            AuthUser user = em.createQuery(
                            "select u from AuthUser u where u.token = :token", AuthUser.class)
                    .setParameter("token", token)
                    .getSingleResult();
            commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return Optional.empty();
        }
    }

    public Optional<AuthUser> findByUsername(@NotNull String username) {
        try {
            begin();
            AuthUser user = em.createQuery(
                            "select u from AuthUser u where u.username ilike :username", AuthUser.class)
                    .setParameter("username", username)
                    .getSingleResult();
            commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            return Optional.empty();
        }
    }

    public Optional<AuthUser> findByEmail(@NotNull String email) {
        try {
            begin();
            AuthUser authUser = em.createQuery("select t from AuthUser  t where t.email ilike :email", AuthUser.class).setParameter("email", email).getSingleResult();
            commit();
            return Optional.ofNullable(authUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            rollback();
            return Optional.empty();
        }
    }
}
