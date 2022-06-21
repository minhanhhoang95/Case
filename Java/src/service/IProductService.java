package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void add(Product newProduct);

    void update(Product newProduct);

    Product findById(long id);

    boolean exist(long id);

    boolean existByName(String name);

    boolean existsById(long id);

    void deleteById(long id);

    List<Product> findAllOrderByPriceASC();


    List<Product> findAllOrderByPriceDESC();

    List<Product> findAllOrderByNameASC();

    List<Product> findAllOrderByNameDESC();


}
