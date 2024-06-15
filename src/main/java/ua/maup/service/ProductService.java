package ua.maup.service;

import ua.maup.model.Category;
import ua.maup.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(String categoryId);
    List<Category> getCategories();

    Product getProduct(String productId);
}
