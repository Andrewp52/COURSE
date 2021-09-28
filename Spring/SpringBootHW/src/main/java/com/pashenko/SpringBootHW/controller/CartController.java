package com.pashenko.SpringBootHW.controller;

import com.pashenko.SpringBootHW.Service.CartService;
import com.pashenko.SpringBootHW.Service.ProductService;
import com.pashenko.SpringBootHW.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @PostMapping("/cart/add")
    public String addProductToCart(HttpServletRequest request, @RequestParam(name = "id", required = true) Long id){
        System.out.println("kuku");
        cartService.add(productService.findById(id));
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("cart/addQuant")
    public String addQuant(@ModelAttribute Product product){
        cartService.addQuant(product);
        return "redirect:/cart";
    }

    @PostMapping("cart/remQuant")
    public String remQuant(@ModelAttribute Product product){
        cartService.remQuant(product);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(Model model){
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("product", new Product());
        model.addAttribute("total", cartService.getTotalPrice());
        return "cart";
    }

}
