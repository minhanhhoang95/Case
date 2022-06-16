package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProduct();

    void add(Product newProduct);

    Product getbyID(int id);

    boolean exisById(int id);

    void update(Product newProduct);

    boolean checkDuplicateId(int id);

    void removeProduct(Product product);


}
