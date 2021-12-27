package ru.geekbrains.handler.factory.reflect;

import ru.geekbrains.handler.Handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// Collects "Handler" classes in a given package, uses annotation for filtering.
// Sorting order is predefined as reversed.
// Scanning a package does only one time.

class HandlersCollector {
    private static List<Class> classes;

    static List<Class> findClasses(String packageName, Class<? extends Handler> annotation) {
        if(classes == null){
            classes = getFromPackage(packageName, annotation);
        }
        return classes;
    }

    private static List<Class> getFromPackage(String packageName, Class<? extends Handler> annotation){
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .filter(Objects::nonNull)
                .filter(aClass -> aClass.getAnnotation(annotation) != null)
                .sorted((o1, o2) -> getOrder(o2, annotation) - getOrder(o1, annotation))
                .collect(Collectors.toList());
    }


    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getOrder(Class<?> cls, Class<? extends Handler> annotation){
        return cls.getAnnotation(annotation).order();
    }
}
