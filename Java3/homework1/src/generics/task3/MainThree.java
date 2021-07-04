package generics.task3;

// Test for task 3

public class MainThree {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();

        appleBox1.addFruit(new Apple());
        appleBox2.addFruit(new Apple());
        orangeBox1.addFruit(new Orange());

        // comparing by weight
        System.out.println("Compare 1 - 1: " + appleBox1.compare(appleBox2));
        appleBox2.addFruit(new Apple());                                                    // for false
        System.out.println("Compare 1 - 2: " + appleBox1.compare(appleBox2));
        System.out.println("Compare APS - ORG: " + appleBox1.compare(orangeBox1));          // Diff types
        System.out.println("-----------------------------------");

        // Moving items
        System.out.printf("Boxes weights: out - %s, in - %s\r\n", appleBox2.getWeight(), appleBox1.getWeight());

        appleBox2.moveFruitsTo(appleBox1);
        System.out.printf("Boxes weights: out - %s, in - %s\r\n", appleBox2.getWeight(), appleBox1.getWeight());

    }
}
