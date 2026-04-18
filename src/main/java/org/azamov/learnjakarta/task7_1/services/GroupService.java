package org.azamov.learnjakarta.task7_1.services;

import jakarta.persistence.EntityManager;
import org.azamov.learnjakarta.task7_1.helper.EntityManagerUtil;
import org.azamov.learnjakarta.task7_1.model.Group;
import org.azamov.learnjakarta.task7_1.model.Student;

import java.util.List;

public class GroupService {

    public void createGroup(String name, int createdBy) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(Group.builder().name(name).createdBy(createdBy).build());
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGroupById(int id) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Groups g WHERE g.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        if (groupId <= 0) throw new IllegalArgumentException("groupId must be positive");
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return em.createQuery("SELECT s FROM Student s WHERE s.groupId = :groupId", Student.class)
                    .setParameter("groupId", groupId)
                    .getResultList();
        }
    }

    public List<Group> getGroups() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return em.createQuery("SELECT g FROM Groups g", Group.class)
                    .getResultList();
        }
    }
}
