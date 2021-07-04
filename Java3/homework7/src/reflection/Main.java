package reflection;

import reflection.tester.TestExecutor;
import reflection.tester.TestExecutor2;

public class Main {

    public static void main(String[] args) {
        System.out.println("executor 1\n");
        TestExecutor.start(SomeClass.class);
        System.out.println("-------------------------");

        System.out.println("executor 2\n");
        TestExecutor2.start(SomeClass.class);
    }
}
