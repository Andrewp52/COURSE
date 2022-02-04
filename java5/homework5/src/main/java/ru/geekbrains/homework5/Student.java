package ru.geekbrains.homework5;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mark")
    private Integer mark;

    public Student() {
    }

    public Student(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(mark, student.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mark);
    }
}
