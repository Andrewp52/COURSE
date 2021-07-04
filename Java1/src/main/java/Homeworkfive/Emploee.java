package Homeworkfive;

public class Emploee {
    private String name;
    private String phone;
    private int income;
    private int age;

    public Emploee(String name, String phone, int income, int age) {
        this.name = name;
        this.phone = phone;
        this.income = income;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void print(){
        System.out.println("Name - " + this.name);
        System.out.println("Phone - " + this.phone);
        System.out.println("Income - " + this.income);
        System.out.println("Age - " + this.age);
    }
}
