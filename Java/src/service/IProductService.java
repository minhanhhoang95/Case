package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void add(Product newProduct);

    void update(Product newProduct);

    Product findById(int id);

    boolean exist(int id);

    boolean existByName(String name);

    boolean existsById(int id);

    void deleteById(int id);

    List<Product> findAllOrderByPriceASC();


    List<Product> findAllOrderByPriceDESC();

    List<Product> findAllOrderByNameASC();

    List<Product> findAllOrderByNameDESC();


}
