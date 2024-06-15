package ua.maup.dao;

import ua.maup.model.Category;
import ua.maup.model.Product;

import java.util.List;

public interface ProductsDao {
	List<Product> getProducts(String categoryId);
	List<Category> getCategories();

	Product getProduct(String productId);

}
