package ru.geekbrains.homework8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework8.exceptions.IllegalEntityStateException;
import ru.geekbrains.homework8.entities.Student;
import ru.geekbrains.homework8.repositories.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    private final StudentRepository repo;

    @Autowired
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> findAll(){
        return repo.findAll();
    }

    public Student findById(Long id){
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Object with id: %s not found", id)));
    }

    public Student save(Student student){
        if(student.getId() != null){
            throw new IllegalEntityStateException("Given object is not fittable to insert: id - not null");
        }
        return repo.save(student);
    }

    public Student update(Student student){
        if(!repo.existsById(student.getId())){
            throw new IllegalEntityStateException("Given object is not fittable to update: does not exist in DB");
        }
        return repo.save(student);
    }

    public void delete(Long id){
        if(!repo.existsById(id)){
            throw new IllegalArgumentException(String.format("Unable to delete object: object with id: %s does not exist in DB", id));
        }
        repo.deleteById(id);
    }
}
