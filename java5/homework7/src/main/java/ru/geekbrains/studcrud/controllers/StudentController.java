package ru.geekbrains.studcrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.studcrud.domain.Student;
import ru.geekbrains.studcrud.service.StudentService;

import java.util.NoSuchElementException;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public String getAllStudents(Model model){
        model.addAttribute("students", service.getAll());
        return "students-list";
    }

    @GetMapping("/edit/{id}")
    public String getEditStudentForm(@PathVariable(name = "id") Long id, Model model){
        Student student = service.findById(id);
        model.addAttribute("student", student);
        return "student-edit-form";
    }

    @PostMapping("/edit")
    public String updateExistingStudent(@ModelAttribute(name = "student") Student student){
        service.update(student);
        return "redirect:/";
    }

    @GetMapping("/addNew")
    public String getNewStudentForm(Model model){
        model.addAttribute("student", new Student());
        return "student-new-form";
    }

    @PostMapping("/addNew")
    public String addNewStudent(@ModelAttribute Student student){
        service.addNew(student);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam(name = "id") Long id){
        service.delete(id);
        return "redirect:/";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(Exception e){
        return new ResponseEntity<>(String.format("Element id: %s not found", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
