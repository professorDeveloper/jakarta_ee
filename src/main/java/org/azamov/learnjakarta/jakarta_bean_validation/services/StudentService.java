package org.azamov.learnjakarta.jakarta_bean_validation.services;

import jakarta.persistence.EntityManager;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.EntityManagerUtil;
import org.azamov.learnjakarta.jakarta_bean_validation.model.Student;

import java.util.List;

public class StudentService {

    public void deleteStudentById(int id) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Student s WHERE s.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return em.createQuery("SELECT s FROM Student s", Student.class)
                    .getResultList();
        }
    }

    public boolean createStudent(Student student) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
