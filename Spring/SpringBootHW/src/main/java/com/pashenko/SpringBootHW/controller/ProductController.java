package com.pashenko.SpringBootHW.controller;

import com.pashenko.SpringBootHW.Service.ProductService;
import com.pashenko.SpringBootHW.entity.Product;
import com.pashenko.SpringBootHW.DAO.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    // Shows all products
    @RequestMapping("/")
    public String showAll(Model model){
        model.addAttribute("products", service.findAll());
        return "allProducts";
    }

    // Shows form for a new product addition
    @RequestMapping("/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "newProduct";
    }

    // Adds a new product using object from form
    // Also it works for update
    @RequestMapping(path = "/addProduct", method = POST)
    public String addProduct(@ModelAttribute Product product){
        service.saveOrUpdate(product);
        return "redirect:/";
    }

    // Shows edit form for product with given id
    @RequestMapping(path = "/editProduct", method = GET)
    public String editProduct(Model model, @RequestParam("id") long id){
        Product p = service.findById(id);
        model.addAttribute("product", p);
        return "editProduct";
    }

    // Deletes product with given id
    @RequestMapping(path = "/deleteProduct", method = GET)
    public String deleteProduct(Model model, @RequestParam("id") long id){
        service.deleteById(id);
        return "redirect:/";
    }

    // Finds product with given id (as url part) and return "result" or "not found" view
    @RequestMapping(path = "/product/{id}", method = GET)
    public String showProductByUrlId(Model model, @PathVariable(value = "id") long id){
        return findProduct(model, id);
    }

    // Finds product with given id (as get parameter) and return "result" or "not found" view
    // Calling by form
    @RequestMapping(path = "/findId", method = GET)
    public String showProductByFormId(Model model, @RequestParam long id){
        return findProduct(model, id);
    }


    private String findProduct(Model model, long id){
        Product p = service.findById(id);
        if(p != null){
            model.addAttribute("product", p);
            return "singleProduct";
        }
        model.addAttribute("id", id);
        return "notFound";
    }

}
