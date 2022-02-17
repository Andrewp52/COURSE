package ru.geekbrains.homework5;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentDAO {
    private final Session session;

    public StudentDAO(Session session) {
        this.session = session;
    }

    public void save(Student student){
        this.session.beginTransaction();
        this.session.persist(student);
        this.session.getTransaction().commit();
    }

    public void save(List<Student> students){
        this.session.beginTransaction();
        students.forEach(this.session::persist);
        this.session.getTransaction().commit();
    }

    public void delete(Student student){
        this.session.beginTransaction();
        this.session.delete(student);
        this.session.getTransaction().commit();
    }

    public List<Student> getAll(){
//        CriteriaBuilder cb = this.session.getCriteriaBuilder();
//        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
//        Root<Student> rootEntry = cq.from(Student.class);
//        CriteriaQuery<Student> all = cq.select(rootEntry);
//        TypedQuery<Student> allQuery = this.session.createQuery(all);
//        return allQuery.getResultList();

        List<Student> students = (List<Student>) session.createQuery("from students").list();
        return students;
    }

    public Student getById(Long id){
        return this.session.get(Student.class, id);
    }

    public void update(Student student){
        this.session.beginTransaction();
        this.session.update(student);
        this.session.getTransaction().commit();
    }
}
