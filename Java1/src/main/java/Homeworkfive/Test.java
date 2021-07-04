package Homeworkfive;

public class Test {
    public static void main(String[] args) {
        Emploee[] staff = new Emploee[5];
        staff[0] = new Emploee("Anna", "1234567", 1000, 45);
        staff[1] = new Emploee("Ivan", "2345678", 1500, 20);
        staff[2] = new Emploee("Petr", "3456789", 2000, 41);
        staff[3] = new Emploee("Susan", "4567890", 2500, 42);
        staff[4] = new Emploee("Andrew", "5678901", 3500, 39);
        for (Emploee e : staff) {
            if(e.getAge() > 40){
                e.print();
                System.out.println();
            }
        }
    }
}
