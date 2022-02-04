package ru.geekbrains.homework5;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class HiberSessionFactoryTest {
    private static SessionFactory factory = HiberSessionFactory.get();

    @Test
    void SessionFactoryNotNull(){
        assertNotNull(factory);
    }

    @Test
    void SessionFactoryIsSingleInstance(){
        SessionFactory factory1 = HiberSessionFactory.get();
        assertSame(factory, factory1);
    }
}
