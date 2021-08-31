package com.pashenko.SpringMVC.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

// Session factory wrapper
@Component
public class DAOGeneral {
    private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public SessionFactory getSessionFactory() {
        return factory;
    }
}
