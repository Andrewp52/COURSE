package ru.geekbrains.homework5;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StudentsGenerator {
    public static List<Student> generateWithRandomMarks(int quantity){
        Random random = new Random();
        List<Student> students = new LinkedList<>();
        for (int i = 1; i <= quantity; i++) {
            students.add(new Student("Student-" + i, random.nextInt((5 - 1) + 1) + 1));
        }
        return students;
    }
}
