package com.pashenko.eshop.controllers;

import com.pashenko.eshop.entities.Product;
import com.pashenko.eshop.services.CartService;
import com.pashenko.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addCartProductQuantity(Model model, HttpSession session, HttpServletRequest request, @ModelAttribute Product product){
        cartService.add(product, session);
        model.addAttribute("product", new Product());
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/rem")
    public String remCartProductQuantity(Model model, HttpSession session, HttpServletRequest request, @ModelAttribute Product product){
        cartService.rem(product, session);
        model.addAttribute("product", new Product());
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("order")
    public String placeOrder(Model model, HttpSession session){
        cartService.save(session);
        model.addAttribute("product", new Product());
        return "redirect:/";
    }

    @GetMapping
    public String showCart(HttpSession session, Model model){
        model.addAttribute("productsMap" ,cartService.getProducts(session));
        model.addAttribute("totalPrice", cartService.getTotalPrice(session));
        model.addAttribute("product", new Product());
        return "cart";
    }

    @GetMapping("/add")
    public String addProductToCart(Model model, HttpSession session, HttpServletRequest request, @RequestParam(name = "id", required = true) Long id){
        cartService.add(productService.findById(id), session);
        model.addAttribute("product", new Product());
        return "redirect:" + request.getHeader("Referer");
    }



}
