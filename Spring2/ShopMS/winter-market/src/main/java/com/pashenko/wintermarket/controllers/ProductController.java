package com.pashenko.wintermarket.controllers;

import com.pashenko.contract.Product;
import com.pashenko.contract.ProductDTO;
import com.pashenko.wintermarket.services.ICategoriesService;
import com.pashenko.wintermarket.services.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductsService productsService;
    @Autowired
    private ICategoriesService categoriesService;

    @GetMapping("/add")
    public String showNewProductForm(Model model){
        model.addAttribute("categories", categoriesService.getAllCategories());
        model.addAttribute("productDTO", new ProductDTO());
        return "new-product-form";
    }

    // Adds a new product and redirects with product flashAttribute if success
    @PostMapping("/add")
    public String addNewProduct(Model model, @ModelAttribute ProductDTO productDTO, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Product p = productsService.addNewProduct(productDTO);
        if(p != null){
            redirectAttributes.addFlashAttribute("recent", p);
        }
        return "redirect:" + request.getHeader("Referer");
    }
}
