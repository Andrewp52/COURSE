package com.pashenko.eshop.controllers;

import com.pashenko.eshop.entities.Product;
import com.pashenko.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public String showAll(
            Model model,
            @RequestParam(name = "s", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "p", required = false, defaultValue = "0") Integer page
    ){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        Page<Product> products = service.findAll(size, page);
        model.addAttribute("product", new Product());
        model.addAttribute("products", products);
        return "products-table";
    }

    @GetMapping("/search")
    public String searchByName(
            Model model,
            @RequestParam(name = "s", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "p", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "title", required = true) String title
    ){
        Page<Product> products = service.findByTitle(title, size, page);
        model.addAttribute("title", title);
        model.addAttribute("product", new Product());
        model.addAttribute("products", products);
        return "products-table";
    }

}
