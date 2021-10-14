package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.entites.ProductDTO;
import com.geekbrains.geekmarketwinter.services.CategoryService;
import com.geekbrains.geekmarketwinter.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public String showNewProductForm(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("productDTO", new ProductDTO());
        return "new-product-form";
    }

    // Adds a new product and redirects with product flashAttribute if success
    @PostMapping("/add")
    public String addNewProduct(Model model, @ModelAttribute ProductDTO productDTO, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Product p = productService.addNewProduct(productDTO);
        if(p != null){
            redirectAttributes.addFlashAttribute("recent", p);
        }
        return "redirect:" + request.getHeader("Referer");
    }
}
