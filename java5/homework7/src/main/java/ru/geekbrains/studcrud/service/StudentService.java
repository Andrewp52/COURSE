package ru.geekbrains.studcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.studcrud.domain.Student;
import ru.geekbrains.studcrud.repositories.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public Student addNew(Student student){
        return repo.save(student);
    }

    public Student update(Student student){
        return repo.save(student);
    }

    public Student findById(Long id){
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException(String.valueOf(id)));
    }

    public List<Student> getAll(){
        return repo.findAll();
    }

    public void delete(Long id){
        if(!repo.existsById(id)){
            throw new NoSuchElementException(String.valueOf(id));
        }
        repo.deleteById(id);
    }
}
