package ru.geekbrains.handler.factory.reflect;

import ru.geekbrains.config.Config;
import ru.geekbrains.handler.Handler;
import ru.geekbrains.handler.MethodHandler;
import ru.geekbrains.handler.factory.MethodHandlerFactory;
import ru.geekbrains.service.ResponseSerializer;
import ru.geekbrains.service.SocketService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

public class ReflectHandlerFactory extends MethodHandlerFactory {
    private final String packName;

    public ReflectHandlerFactory(Package pack, SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        super(socketService, responseSerializer, config);
        this.packName = pack.getName();
    }

    @Override
    public MethodHandler create() {
        List<Class> classes = HandlersCollector.findClasses(this.packName, Handler.class);
        return assemblyChain(classes);
    }

    // Assembling chain with iterator
    private MethodHandler assemblyChain(List<Class> classes){
        Iterator<Class> iterator = classes.listIterator();
        MethodHandler inner = null;
        while (iterator.hasNext()){
            MethodHandler handler = createHandler(iterator.next(), inner);
            inner = handler;
        }
        return inner;
    }

    // Creating instance of given class including previously created "inner"
    private MethodHandler createHandler(Class<?> cls, MethodHandler inner) {
        String method = cls.getAnnotation(Handler.class).method();
        Constructor constructor = getConstructor(cls);

        MethodHandler handler = null;
        try {
            handler = (MethodHandler) constructor.newInstance(
                    method,
                    inner,
                    super.service,
                    super.serializer,
                    super.config
            );
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return handler;
    }

    // Retrieves constructor for given class
    private Constructor getConstructor(Class cls) {
        Constructor constructor = null;
        try {
            constructor = cls.getConstructor(
                    String.class,
                    MethodHandler.class,
                    SocketService.class,
                    ResponseSerializer.class,
                    Config.class
            );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return constructor;
    }
}
