package ru.geekbrains.homework5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.*;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentDAOTest {
    private static SessionFactory factory = HiberSessionFactory.get();
    private Session session = factory.openSession();
    private StudentDAO dao = new StudentDAO(session);;

    @BeforeEach
    void cleanTableAndSetAutoIncrement(){
        this.session.beginTransaction();
        NativeQuery query = this.session.createSQLQuery("TRUNCATE `academy`.`students`");
        query.addQueryHint("ALTER TABLE `academy`.`students` AUTO_INCREMENT = 1");
        query.executeUpdate();
        this.session.getTransaction().commit();
        this.session.clear();
    }

    @Test
    @Order(1)
    void insertOneEntityAndSelectByIdWithCache(){
        Student student = new Student("Name", 5);
        this.dao.save(student);
        Student selected = this.dao.getById(student.getId());

        assertSame(student, selected);
    }

    @Test
    @Order(2)
    void insertOneEntityAndSelectByIdWithoutCache(){
        Student student = new Student("Name", 5);
        this.dao.save(student);
        this.session.detach(student);                                // Detach for non-cached select
        Student selected = this.dao.getById(student.getId());

        assertNotSame(student, selected);
        assertEquals(student, selected);
    }

    @Test
    @Order(3)
    void insertOneAndUpdateSelectByIdReturnUpdated(){
        Student student = new Student("Name", 5);
        this.dao.save(student);
        student.setMark(3);
        this.dao.update(student);
        this.session.detach(student);                                // Detach for non-cached select
        Student selected = dao.getById(student.getId());

        assertNotSame(student, selected);
        assertEquals(student, selected);
    }

    @Test
    @Order(4)
    void insertOneDeleteAndSelectWillReturnNull(){
        Student student = new Student("Name", 5);
        this.dao.save(student);
        dao.delete(student);
        Student selected = dao.getById(student.getId());
        assertNull(selected);
    }

    @Test
    @Order(5)
    void insertThreeSelectAllWillReturnThreeEntities(){
        Student student1 = new Student("Name1", 5);
        Student student2 = new Student("Name2", 4);
        Student student3 = new Student("Name3", 3);
        dao.save(student1);
        dao.save(student2);
        dao.save(student3);
        List<Student> selected = dao.getAll();

        assertEquals(3, selected.size());
        assertIterableEquals(List.of(student1, student2, student3), selected);
    }

    @Test
    @Order(6)       // Table won`t clear after test. Generated records wil be persisted. (Task requirement)
    void generateThousandInsertAndCountReturnThousand(){
        List<Student> students = StudentsGenerator.generateWithRandomMarks(1000);
        dao.save(students);
        NativeQuery query = session.createSQLQuery("SELECT COUNT(*) FROM `academy`.`students`");
        BigInteger count = (BigInteger) query.getResultList().get(0);

        assertEquals(1000L, count.longValue());
    }

    @AfterAll
    static void closeFactory(){
        factory.close();
    }
}
