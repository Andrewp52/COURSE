package homeworkone;

public class FirstWork {
    boolean bl = false;
    byte bt = 127;
    char ch = 'a';
    short sh = 10;
    int i = 100;
    float fl = 1.4f;
    long l = 1000L;
    double dbl = 2.0d;
    String str = "Hi!";

    public static void main(String[] args) {
        System.out.println(calculate(2.2f, 3.0f, 4.0f, 3.2f));
        System.out.println(isSumBetween(11, 9));
        checkPositive(-4);
        System.out.println(isNegative(5));
        sayHello("Андрей");
        checkLeapYear(1512);
    }

    private static float calculate(float a, float b, float c, float d){
        return a * (b + (c / d));
    }

    private static boolean isSumBetween(int a, int b){
        return a + b >= 10 && a + b <= 20;
    }

    private static void checkPositive(int num){
        if (num >= 0){
            System.out.println(num + " положительное");
        }
        else {
            System.out.println(num + " отрицательное");
        }
    }

    private static boolean isNegative(int num){
        return num <0;
    }

    private static void sayHello(String name){
        System.out.println("Привет, " + name + "!");
    }

    private static void checkLeapYear(int year){
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            System.out.println(year + " високосный");
        }
        else {
            System.out.println(year + " не високосный");
        }
    }
}
