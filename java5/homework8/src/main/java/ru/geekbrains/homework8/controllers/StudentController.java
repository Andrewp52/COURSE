package ru.geekbrains.homework8.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework8.entities.Student;
import ru.geekbrains.homework8.exceptions.ExceptionDTO;
import ru.geekbrains.homework8.exceptions.IllegalEntityStateException;
import ru.geekbrains.homework8.services.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public Student addNewStudent(@RequestBody Student student){
        return service.save(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student){
        return service.update(student);
    }

    @DeleteMapping("/{id}")
    public Object deleteStudent(@PathVariable Long id ){
        service.delete(id);
        return HttpStatus.OK;
    }

    @ExceptionHandler(IllegalEntityStateException.class)
    public ExceptionDTO handleIllegalEntityState(IllegalEntityStateException e, HttpServletResponse response){
        response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        return new ExceptionDTO("IllegalEntityState", e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionDTO handleIllegalArgument(IllegalArgumentException e, HttpServletResponse response){
        response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        return new ExceptionDTO("IllegalArgument", e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ExceptionDTO handleNoSuchElement(NoSuchElementException e, HttpServletResponse response){
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return new ExceptionDTO("NoSuchElement", e.getMessage());
    }
}
