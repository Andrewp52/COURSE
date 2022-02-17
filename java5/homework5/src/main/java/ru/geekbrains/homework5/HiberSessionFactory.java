package ru.geekbrains.homework5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HiberSessionFactory {
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private HiberSessionFactory(){
    }

    public static SessionFactory get(){
        return sessionFactory;
    }

}
