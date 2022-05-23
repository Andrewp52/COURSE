package ru.geekbrains.homework8.entities;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    @NonNull
    private String firstName;

    @Column(name = "lasstName")
    @NonNull
    private String lastName;

    @Column(name = "login", unique = true)
    @NonNull
    private String login;

    @Column(name = "password")
    @NonNull
    private String password;

    public Student(Long id, String firstName, String lastName, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String paswword) {
        this.password = paswword;
    }
}
