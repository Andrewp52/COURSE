package ru.geekbrains.homework8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homework8.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
