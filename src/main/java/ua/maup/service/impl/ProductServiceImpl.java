package ua.maup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.maup.dao.ProductsDao;
import ua.maup.model.Category;
import ua.maup.model.Product;
import ua.maup.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsDao productsDao;

    @Override
    public List<Product> getProducts(String categoryId) {
        return productsDao.getProducts(categoryId);
    }

    @Override
    public List<Category> getCategories() {
        return productsDao.getCategories();
    }

    @Override
    public Product getProduct(String productId) {
        return productsDao.getProduct(productId);
    }

}

