package reflection;

import reflection.tester.AfterSuite;
import reflection.tester.BeforeSuite;
import reflection.tester.Test;

public class SomeClass {
    @BeforeSuite                                                               // Will run first
    void doSomeBefore(){
        System.out.println("Before suite method");
    }

    @AfterSuite                                                                // Will run last
    void doSomeAfter(){
        System.out.println("After suite method");
    }
    @Test
    void doTest1(){
        System.out.println("method 1 pri 10");
    }

    @Test(priority = 3)
    void doTest2(){
        System.out.println("method 2 pri 3");
    }

    @Test(priority = 1)
    void doTest3(){
        System.out.println("method 3 pri 1");
    }

    @Test(priority = 5)
    void doTest4(){
        System.out.println("method 4 pri 5");
    }

    void exclusionMethod(){
        System.out.println("EX Method");
    }                // Won't run

}
