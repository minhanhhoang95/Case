package service;

import model.Product;
import utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ProductService implements IProductService {
    public final static String PATH = "D:\\Module2\\Case\\Java\\data\\product.csv";
    private static ProductService instance;

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
        return instance;
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.readData(PATH);
        for (String record : records) {
            products.add(Product.parse(record));
        }
        return products;
    }

    @Override
    public void add(Product newProduct) {
        List<Product> products = findAll();
        newProduct.setCreatedAt(Instant.now());
        products.add(newProduct);
        CSVUtils.writeData(PATH, products);
    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                String title = newProduct.getTitle();
                if (title != null && !title.isEmpty()) {
                    product.setTitle(title);
                }
                Integer quantity = newProduct.getQuantity();
                if (quantity != null) {
                    product.setQuantity(quantity);
                }
                Double price = newProduct.getPrice();
                if (price != null) {
                    product.setPrice(price);
                }
                String description = newProduct.getDescription();
                if (description != null && !description.isEmpty()) {
                    product.setDescription(description);
                }
                product.setUpdatedAt(Instant.now());
                CSVUtils.writeData(PATH, products);
                break;
            }
        }
    }

    public Product findById(int id) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean exist(int id) {
        return findById(id) != null;
    }

    public boolean existByName(String name) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getTitle().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean existsById(int id) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void deleteById(int id) {
        List<Product> products = findAll();
        products.removeIf(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                return product.getId() == id;
            }
        });
        CSVUtils.writeData(PATH, products);
    }

    @Override
    public List<Product> findAllOrderByPriceASC() {
        List<Product> products = new ArrayList<>(findAll());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                double result = o1.getPrice() - o2.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });

        return products;
    }

    @Override
    public List<Product> findAllOrderByPriceDESC() {
        List<Product> products = new ArrayList<>(findAll());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                double result = o2.getPrice() - o1.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });

        return products;
    }
    public List<Product> findAllOrderByNameASC(){
        List<Product> products = new ArrayList<>(findAll());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return products;
    }
    @Override
    public List<Product> findAllOrderByNameDESC(){
        List<Product> products = new ArrayList<>(findAll());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getTitle().compareTo(o1.getTitle());
            }
        });
        return products;
    }
}
