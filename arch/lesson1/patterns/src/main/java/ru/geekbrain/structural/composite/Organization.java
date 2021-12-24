package ru.geekbrain.structural.composite;

import java.util.LinkedList;
import java.util.List;

public class Organization {
    private List<Employee> employees = new LinkedList<>();

    public void addEmployee(Employee employee)
    {
        this.employees.add(employee);
    }

    public float getNetSalaries(){
        return this.employees.stream()
                .map(Employee::getSalary)
                .reduce(Float::sum).get();
    }

}
