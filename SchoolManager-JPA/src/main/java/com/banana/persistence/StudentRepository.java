package com.banana.persistence;

import com.banana.models.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

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
    @Transactional
    public Student update(Student estudiante) throws SQLException {
        Student aStd = em.find(Student.class, estudiante.getId());
        aStd.setNombre(estudiante.getNombre());
        em.flush();
        aStd.setApellido(estudiante.getApellido());
//        em.merge(estudiante);
        return estudiante;
    }

    @Override
    public Student get(int idx) throws SQLException {
//        TypedQuery query = em.createQuery("SELECT s FROM Student s", Student.class);
        TypedQuery query = em.createNamedQuery("Student.GetByIdx", Student.class);
        query.setFirstResult(idx).setMaxResults(1);
//        List<Student> estudiantes = query.getResultList();
        Student elStd = (Student) query.getSingleResult();

        return elStd;
    }

    @Override
    public Student getById(Long id) throws SQLException {
        return em.find(Student.class, id);
    }
}
