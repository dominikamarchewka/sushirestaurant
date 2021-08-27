package restaurant.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import restaurant.app.Models.Product;
import restaurant.app.Repository.ProductRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private ProductRepository productRepository;
    private HttpSession session;

    HomeController(ProductRepository productRepository, HttpSession session){
        this.productRepository = productRepository;
        this.session = session;
    }

    @RequestMapping("/orderOnline")
    public String orderOnline (Model model){
        List<Product> productList = productRepository.findAllByStatus("Dostępne");
        model.addAttribute("products", productList);
        return "orderOnline";
    }

    @RequestMapping("/orderOnline/przystawki")
    public String orderOnlineStarters (Model model){
        List<Product> productList = productRepository.findAllByCategory_CategoryNameAndStatus("Przystawki", "Dostępne");
        model.addAttribute("products", productList);
        return "orderOnline";
    }

    @RequestMapping("/orderOnline/salatki")
    public String orderOnlineSalads (Model model) {
        List<Product> productList = productRepository.findAllByCategory_CategoryNameAndStatus("Sałatki", "Dostępne");
        model.addAttribute("products", productList);
        return "orderOnline";
    }

    @RequestMapping("/orderOnline/sushi")
    public String orderOnlineSushi (Model model) {
        List<Product> productList = productRepository.findAllByCategory_CategoryNameAndStatus("Sushi", "Dostępne");
        model.addAttribute("products", productList);
        return "orderOnline";
    }

    @RequestMapping("/contact")
    public String contact (){
        return "contact";
    }

    @RequestMapping("/delivery")
    public String delivery (){
        return "delivery";
    }

    @RequestMapping("/orderOnline/return")
    public String returnOrder (Model model) {
        List<Product> productList = productRepository.findAllByStatus("Dostępne");
        model.addAttribute("products", productList);
        session.removeAttribute("cart");
        return "orderOnline";
    }
}
