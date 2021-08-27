package restaurant.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import restaurant.app.Models.Product;
import restaurant.app.Repository.ProductRepository;
import restaurant.app.Service.CartService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CartItemController {
    private ProductRepository productRepository;
    private CartService cartService;

    @Autowired
    public CartItemController(ProductRepository productRepository, CartService cartService) {
        this.productRepository = productRepository;
        this.cartService = cartService;
    }

    @RequestMapping("/cart")
    public String Cart(Model model) {
        double total = cartService.getTotal();
        model.addAttribute("total", total);
        return "cart";
    }

    @GetMapping("/cart/addProduct/{id}")
    public String addProductToCart(@PathVariable("id") Integer idProduct, Model model) {
        cartService.addProductToCart(idProduct);
        List<Product> productList = productRepository.findAllByStatus("Dostępne");
        model.addAttribute("products", productList);
        return "orderOnline";
    }

    @GetMapping("/cart/removeProduct/{id}")
    public String removeProductFromCart (@PathVariable("id") Integer idProduct, Model model) {
        cartService.removeProductFromCart(idProduct);
        double total = cartService.getTotal();
        model.addAttribute("total", total);
        return "cart";
    }

    @RequestMapping("/cart/update")
    public String updateProductFromCart (HttpServletRequest request, Model model) {
        cartService.updateProductFromCart(request);
        double total = cartService.getTotal();
        model.addAttribute("total", total);
        return "cart";
    }
}
