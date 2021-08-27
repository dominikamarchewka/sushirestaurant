package restaurant.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import restaurant.app.Models.Category;
import restaurant.app.Models.Product;
import restaurant.app.Repository.CategoryRepository;
import restaurant.app.Repository.ProductRepository;

@Controller
public class ProductController {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private Category przystawki = new Category("Przystawki");
    private Category sałatki = new Category("Sałatki");
    private Category sushi = new Category("Sushi");

    @Autowired
    ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/addProduct")
    public String form() {
        return "addProduct";
    }

    @RequestMapping("/saveProduct")
    public String addProduct(@ModelAttribute Product product,
                             Model model)
            throws Exception {
        model.addAttribute("product", product);
        if (product.getCategory().getCategoryName().equals("Przystawki")) {
            przystawki.addProduct(product);
            categoryRepository.save(przystawki);
        } else if (product.getCategory().getCategoryName().equals("Sałatki")) {
            sałatki.addProduct(product);
            categoryRepository.save(sałatki);
        } else if (product.getCategory().getCategoryName().equals("Sushi")) {
            sushi.addProduct(product);
            categoryRepository.save(sushi);
        }
        return "saveProduct";
    }

    @RequestMapping("/showAllProducts")
    public String showProduct(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "allProducts";
    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(Integer id, Model model) {
        productRepository.deleteById(id);
        model.addAttribute("products", productRepository.findAll());
        return "allProducts";
    }

    @RequestMapping("/editProduct")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("products", productRepository.findByIdProduct(id));
        return "updateProduct";
    }

    @RequestMapping("/updateProduct")
    public String update(
            @ModelAttribute Product product, Model model)
            throws Exception {
        if (product.getCategory().getCategoryName().equals("Przystawki")) {
            przystawki.addProduct(product);
            categoryRepository.save(przystawki);
            productRepository.save(product);
        } else if (product.getCategory().getCategoryName().equals("Sałatki")) {
            sałatki.addProduct(product);
            categoryRepository.save(sałatki);
            productRepository.save(product);
        } else if (product.getCategory().getCategoryName().equals("Sushi")) {
            sushi.addProduct(product);
            categoryRepository.save(sushi);
            productRepository.save(product);
        }
        model.addAttribute("products", productRepository.findAll());
        return "allProducts";
    }

    @RequestMapping("/findCategory")
    public String find(@RequestParam("categoryName") String categoryName, Model model) {
        model.addAttribute("products", productRepository.findAllByCategory_CategoryNameAndStatus(categoryName, "Dostępne"));
        return "allProducts";
    }
}
