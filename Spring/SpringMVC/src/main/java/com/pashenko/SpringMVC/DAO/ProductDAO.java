package com.pashenko.SpringMVC.DAO;

import com.pashenko.SpringMVC.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private DAOGeneral general;
    private SessionFactory factory;

    // Ony this option works
    @Autowired
    public void setGeneral(DAOGeneral general) {
        this.general = general;
        factory = general.getSessionFactory();
    }

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
