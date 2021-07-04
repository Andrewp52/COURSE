package reflection.tester;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// VARIANT 1
public class TestExecutor {
    static boolean before = false, after = false;           // single instance methods (Before After) validation flags
    private TestExecutor(){

    }

    public static void start(Class<?> testClass) {
        before = false; after = false;
        Queue<Method> methods = extractMethods(testClass);
        if(methods.size() == 0){
            System.out.println("No suitable methods for execution");
            return;
        }
        try {
            executeMethods(methods, testClass.getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void start(String testClassName){
        try {
            start(Class.forName(testClassName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void executeMethods(Queue<Method> methods, Object obj, Object... args){
        Method m;
        try{
            while (!methods.isEmpty()){
                m = methods.poll();
                m.setAccessible(true);
                m.invoke(obj, args);
                m.setAccessible(false);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // Extracts all methods with annotations
    private static Queue<Method> extractMethods(Class<?> testClass){
        Queue<Method> queue = new PriorityQueue<>(new MethComparator());
        for (Method m: testClass.getDeclaredMethods()) {
            if(m.getDeclaredAnnotations().length == 0){
                continue;
            }
            validateMethod(m);
            queue.add(m);
        }
        return queue;
    }

    // Validates methods by annotations
    private static void validateMethod(Method m) throws RuntimeException{
        if(m.isAnnotationPresent(Test.class)){
            Test tst = m.getDeclaredAnnotation(Test.class);
            if(tst.priority() < 1 || tst.priority() > 10){
                throw new RuntimeException("Test priority out of range (1 - 10): " + tst.priority());
            }
        } else {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (before) {
                    throw new RuntimeException("Only one BeforeSuite instance allowed");
                }
                before = true;
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (after) {
                    throw new RuntimeException("Only one AfterSuite instance allowed");
                }
                after = true;
            } else {
                throw new RuntimeException("Invalid annotation found");
            }
        }
    }

    private static class MethComparator implements Comparator<Method>{
        @Override
        public int compare(Method o1, Method o2) {
            Test o1Tst = o1.getDeclaredAnnotation(Test.class);
            Test o2Tst = o2.getDeclaredAnnotation(Test.class);
            if(o1Tst != null && o2Tst != null){
                return o1Tst.priority() - o2Tst.priority();
            }
            if(o1.isAnnotationPresent(BeforeSuite.class) || o2.isAnnotationPresent(AfterSuite.class)){
                return -1;
            }
            if(o2.isAnnotationPresent(BeforeSuite.class) || o1.isAnnotationPresent(AfterSuite.class)){
                return 1;
            }
            return 0;
        }
    }
}
