package com.pashenko.lesson1.controller;

// TOMCAT 10 REQUIRES SERVLET V5 !!!!
import com.pashenko.lesson1.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        List<Product> list = Product.generate(10);
        list.forEach(product -> printWriter.println("<p>" + product.toString()));
    }
}
