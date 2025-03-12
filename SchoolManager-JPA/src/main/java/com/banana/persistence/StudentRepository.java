package com.banana.persistence;

import com.banana.models.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Repository
public class StudentRepository implements  StudentsRepositoryInf{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void add(Student estudiante) throws SQLException {
        if(estudiante.isValid()) em.persist(estudiante);
        else throw new SQLException("Estudiante no válido:"+estudiante);
    }

    @Override
    public Student update(Student estudiante) throws SQLException {
        return null;
    }

    @Override
    public Student get(int idx) throws SQLException {
        return null;
    }

    @Override
    public Student getById(Long id) throws SQLException {
        return em.find(Student.class, id);
    }
}
