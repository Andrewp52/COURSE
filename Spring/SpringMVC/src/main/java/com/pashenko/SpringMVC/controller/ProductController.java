package com.pashenko.SpringMVC.controller;

import com.pashenko.SpringMVC.entity.Product;
import com.pashenko.SpringMVC.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Component
@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    // Shows all products
    @RequestMapping("/")
    public String showAll(Model model){
        model.addAttribute("products", service.getAll());
        return "allProducts";
    }

    // Shows form for a new product addition
    @RequestMapping("/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "newProduct";
    }

    // Adds a new product using object from form
    @RequestMapping(path = "/addProduct", method = POST)
    public String addProduct(@ModelAttribute Product product){
        service.addProduct(product);
        return "redirect:/";
    }

    // Finds product with given id (as url part) and return "result" or "not found" view
    @RequestMapping(path = "/product/{id}", method = GET)
    public String showProductByUrlId(Model model, @PathVariable(value = "id") int id){
        return findProduct(model, id);
    }

    // Finds product with given id (as get parameter) and return "result" or "not found" view
    // Calling by form
    @RequestMapping(path = "/findId", method = GET)
    public String showProductByFormId(Model model, @RequestParam int id){
        return findProduct(model, id);
    }


    private String findProduct(Model model, int id){
        Product p = service.geyById(id);
        if(p != null){
            model.addAttribute("product", p);
            return "singleProduct";
        }
        model.addAttribute("id", id);
        return "notFound";
    }

}
