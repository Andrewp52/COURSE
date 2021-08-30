package com.pashenko.SpringMVC.DAO;

import com.pashenko.SpringMVC.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public void saveOrUpdate(Product product){
        Session s = factory.getCurrentSession();
        s.getTransaction().begin();
        s.saveOrUpdate(product);
        s.getTransaction().commit();
    }

    public Product findById(long id){
        Session s = factory.getCurrentSession();
        s.getTransaction().begin();
        Product p = s.find(Product.class, id);
        s.getTransaction().commit();
        return p;
    }

    public List<Product> findAll(){
        Session s = factory.getCurrentSession();
        s.getTransaction().begin();
        List<Product> res = s.createQuery("from products").getResultList();
        s.getTransaction().commit();
        return res;
    }

    public void deleteById(long id){
        Product p = new Product();
        p.setId(id);
        Session s = factory.getCurrentSession();
        s.getTransaction().begin();
        s.delete(p);
        s.getTransaction().commit();
    }
}
