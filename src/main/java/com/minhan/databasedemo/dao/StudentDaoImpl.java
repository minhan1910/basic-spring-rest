package com.minhan.databasedemo.dao;

import com.minhan.databasedemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    private EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student getById(Integer id) {
        var stdById = entityManager.find(Student.class, id);
        return stdById;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> studentsQueried = entityManager.createQuery("FROM Student ORDER BY id DESC", Student.class);
        return studentsQueried.getResultList();
    }

    @Override
    @Transactional
    public Student update(Student student) {
        Student studentFromDb = entityManager.find(Student.class, student.getId());

        studentFromDb.setEmail(student.getEmail());
        studentFromDb.setFirstName(student.getFirstName());
        studentFromDb.setLastName(student.getLastName());

        Student studentUpdated = entityManager.merge(studentFromDb);

        return studentUpdated;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Student studentToDelete = entityManager.find(Student.class, id);
        entityManager.remove(studentToDelete);
    }

    @Override
    @Transactional
    public int deleteAll() {
        var affected = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return affected;
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(*) FROM Student", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Student findByEmail(String email) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE email LIKE :email", Student.class);

        query.setParameter("email", "%" + email + "%");

        List<Student> resultList = query.getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
