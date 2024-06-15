package ua.maup.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.maup.service.ProductService;
import ua.maup.web.controller.ProductController;

import javax.servlet.http.HttpSession;

@Controller
public class ProductControllerImpl implements ProductController {

    @Autowired
    ProductService productService;

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public String showProducts(@RequestParam(name = "category", required = false) String category, HttpSession session) {

        session.setAttribute("categories", productService.getCategories());
        session.setAttribute("products", productService.getProducts(category));

        return "products";
    }
}
