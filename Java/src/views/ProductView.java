package views;

import model.Product;
import service.IProductService;
import service.ProductService;
import utils.AppUtils;
import utils.InstantUtils;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final IProductService productService;
    private final Scanner sc = new Scanner(System.in);

    public ProductView() {
        productService = ProductService.getInstance();
    }

    public void showProducts(InputOption inputOption) {
        System.out.println("════════════════════════════════════════════════ Wine List ════════════════════════════════════════════════");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s %-20s %-20s\n", "Id", "Wine Name ", "Price", "Quantity", "Date Created", "Date Edit", "Description");
        for (Product product : productService.findAll()) {
            System.out.printf("%-15d %-30s %-25s %-10d %-20s %-20s %-20s\n",
                    product.getId(),
                    product.getTitle(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    InstantUtils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt()),
                    product.getDescription()
            );
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
        if (inputOption == InputOption.SHOW) {
            AppUtils.isRetry(InputOption.SHOW);
        }
    }

    public void add() {
        do {
            long id = System.currentTimeMillis() / 1000;
            String title = inputTitle(InputOption.ADD);
            double price = inputPrice(InputOption.ADD);
            int quantity = inputQuantity(InputOption.ADD);
            String description = inputDescription();
            Product product = new Product(id, title, price, quantity, description);
            productService.add(product);
            System.out.println(" successfully added\n");

        } while (AppUtils.isRetry(InputOption.ADD));
    }

    public void update() {
        boolean isRetry;
        do {
            showProducts(InputOption.UPDATE);
            long id = inputId(InputOption.UPDATE);
            System.out.print("╔═══════════════════════════════════════════════════════════════════════════════╗" +
                    "\n║                                                                               ║" +
                    "\n║                                [Edit Product]                                 ║" +
                    "\n║                                                                               ║" +
                    "\n║                             ■  [1]   Edit Name                                ║" +
                    "\n║                             ■  [2]   Edit Quantity                            ║" +
                    "\n║                             ■  [3]   Edit Address                             ║" +
                    "\n║                             ■  [4]   Come Back                                ║" +
                    "\n║                             ■  [0]   Exit                                     ║" +
                    "\n║                                                                               ║" +
                    "\n╚═══════════════════════════════════════════════════════════════════════════════╝" +
                    "\n");
            System.out.print(" ⭆ ");
            int option = AppUtils.retryChoose(0, 4);
            Product newProduct = new Product();
            newProduct.setId(id);
            switch (option) {
                case 1:
                    String title = inputTitle(InputOption.UPDATE);
                    newProduct.setTitle(title);
                    productService.update(newProduct);
                    System.out.println("successfully updated");
                    break;
                case 2:
                    int quantity = inputQuantity(InputOption.UPDATE);
                    newProduct.setQuantity(quantity);
                    productService.update(newProduct);
                    System.out.println("successfully updated");
                    break;
                case 3:
                    double price = inputPrice(InputOption.UPDATE);
                    newProduct.setPrice(price);
                    productService.update(newProduct);
                    System.out.println("successfully updated");
                    break;
                case 0:
                    AppUtils.exit();
            }
            isRetry = option != 4 && AppUtils.isRetry(InputOption.UPDATE);
        } while (isRetry);
    }

    public void remove() {
        showProducts(InputOption.DELETE);
        int id;
        while (!productService.exist(id = inputId(InputOption.DELETE))) {
            System.out.println("No products to be deleted were found");
            System.out.println(" Press 'y' to add more \t|\t 'q' to come back \t|\t 't' to exit the program");
            System.out.print(" ⭆ ");
            String option = sc.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Choose the wrong function! Please choose again");
                    break;
            }
        }

        System.out.println("   ❄ ❄ ❄ ❄ REMOVE COMFIRM ❄ ❄ ❄");
        System.out.println("  ❄      1. Press 1 to delete     ❄");
        System.out.println(" ❄       2. Press 2 to go back     ❄");
        System.out.println("❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            productService.deleteById(id);
            System.out.println("Remove Successfully");
            AppUtils.isRetry(InputOption.DELETE);
        }

    }
    private String inputTitle(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Enter wine name: ");
                break;
            case UPDATE:
                System.out.println("Enter the name you want to edit: ");
                break;
        }
        System.out.print("⭆ ");
        return sc.nextLine();
    }

    private String inputDescription() {
        System.out.println("Wine Description: ");
        System.out.print("⭆ ");
        return sc.nextLine();
    }

    private int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Enter Id");
                break;
            case UPDATE:
                System.out.println("Enter the id you want to edit");
                break;
            case DELETE:
                System.out.println("Enter the id you need to delete:");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = productService.existsById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("This id already exists. Please enter another id!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Id not found! Please re-enter");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Enter quantity: ");
                break;
            case UPDATE:
                System.out.println("Enter the quantity you want to edit: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0)
                System.out.println("Quantity > 0");
        } while (quantity <= 0);
        return quantity;
    }

    private double inputPrice(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Enter wine price:");
                break;
            case UPDATE:
                System.out.println("Enter the price you want to edit: ");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price <= 0)
                System.out.println("Price > 0");
        } while (price <= 0);
        return price;
    }

    public void showProductsSort(InputOption inputOption, List<Product> products) {
        System.out.println("════════════════════════════════════════════════ Wine List ════════════════════════════════════════════════");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s %-20s %-20s\n", "Id", "Wine Name ", "Price", "Quantity", "Date Created", "Date Edit", "Description");
        for (Product product : products) {
            System.out.printf("%-15d %-30s %-25s %-10d %-20s %-20s %-20s\n",
                    product.getId(),
                    product.getTitle(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    InstantUtils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt()),
                    product.getDescription()
            );
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void sortByPriceOrderByASC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceASC());
    }

    public void sortByPriceOrderByDESC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceDESC());
    }

    public void sortByNameOrderByASC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByNameASC());
    }

    public void sortByNameOrderByDESC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByNameDESC());
    }

}
