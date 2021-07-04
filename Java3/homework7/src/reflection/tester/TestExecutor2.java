package reflection.tester;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

// VARIANT 2
public class TestExecutor2 {
    private TestExecutor2() {
    }

    public static void start(Class testClass){
        List<Method> toExecute = getSequence(testClass);
        if(toExecute.size() == 0){
            System.out.println("No suitable methods found.");
        }
        try {
            executeMethods(toExecute, testClass.getConstructor().newInstance());
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

    // Collects methods to execute. Sequence (Before - Tests - After)
    private static List<Method> getSequence(Class testClass){
        List<Method> res = new LinkedList<>();
        Method meth;
        if((meth = getBefore(testClass)) != null){
            res.add(meth);
        }
        res.addAll(getTests(testClass));
        if((meth = getAfter(testClass)) != null){
            res.add(meth);
        }
        return res;
    }

    // Extracts methods with given annotation
    private static List<Method> getMethods(Class from, Class<? extends Annotation> annotation){
        List<Method> res = new LinkedList<>();
        for (Method m: from.getDeclaredMethods()){
            if(m.isAnnotationPresent(annotation)){
                res.add(m);
            }
        }
        return res;
    }

    private static Method getBefore(Class testClass){
        List<Method> res;
        if((res = getMethods(testClass, BeforeSuite.class)).size() != 0){
            if(res.size() > 1){
                throw new RuntimeException("Only one instance of BeforeSuite is allowed");
            }
        }
        return res.get(0);
    }

    private static Method getAfter(Class testClass){
        List<Method> res;
        if((res = getMethods(testClass, AfterSuite.class)).size() != 0){
            if(res.size() > 1){
                throw new RuntimeException("Only one instance of BeforeSuite is allowed");
            }
        }
        return res.get(0);
    }

    private static List<Method> getTests(Class testClass){
        List<Method> res;
        if((res = getMethods(testClass, Test.class)).size() != 0){
            for (Method m: res) {
                if(m.getDeclaredAnnotation(Test.class).priority() < 1 || m.getDeclaredAnnotation(Test.class).priority() > 10){
                    throw new RuntimeException("Test priority is out of range (1 - 10)");
                }
            }
        }
        res.sort((o1, o2) -> {
            Test o1tst = o1.getDeclaredAnnotation(Test.class);
            Test o2tst = o2.getDeclaredAnnotation(Test.class);
            return o1tst.priority() - o2tst.priority();
        });
        return res;
    }

    // Executes methods sequence at given object
    private static void executeMethods(List<Method> methods, Object obj, Object... args){
        try{
            for (Method m : methods) {
                m.setAccessible(true);
                m.invoke(obj, args);
                m.setAccessible(false);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
