package ru.geekbrain.structural.composite;

public class Usage {
    public static void main(String[] args) {
        Organization organization = new Organization();
        organization.addEmployee(new Designer("vasya", 10f));
        organization.addEmployee(new Designer("ira", 15f));
        organization.addEmployee(new Developer("petya", 20f));

        System.out.println(organization.getNetSalaries());
    }
}
