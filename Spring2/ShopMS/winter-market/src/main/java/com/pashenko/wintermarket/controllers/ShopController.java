package com.pashenko.wintermarket.controllers;

import com.pashenko.contract.Product;
import com.pashenko.wintermarket.services.IProductsService;
import com.pashenko.wintermarket.services.ShoppingCartService;
import com.pashenko.wintermarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController{
    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 5;

    private UserService userService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    private IProductsService productsService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String shopPage(Model model,
                           @RequestParam(value = "page") Optional<Integer> page,
                           @RequestParam(value = "word", required = false) String word,
                           @RequestParam(value = "min", required = false) Double min,
                           @RequestParam(value = "max", required = false) Double max
    ) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        StringBuilder filters = new StringBuilder();
        if (word != null) {
            filters.append("&word=" + word);
        }
        if (min != null) {
            filters.append("&min=" + min);
        }
        if (max != null) {
            filters.append("&max=" + max);
        }

        Page<Product> products = productsService.getProducts(currentPage, PAGE_SIZE, word, min, max);

        model.addAttribute("products", products.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", products.getTotalPages());

        model.addAttribute("filters", filters.toString());

        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("word", word);


        return "shop-page";
    }

    private final static String QUEUE_NAME = "hello";

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(Model model, @PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
        String referrer = httpServletRequest.getHeader("referer");

        return "redirect:" + referrer;
    }

    @GetMapping("/order/fill")
    @ResponseBody
    public String fillNewOrder(){

        return "Success";
    }
}
