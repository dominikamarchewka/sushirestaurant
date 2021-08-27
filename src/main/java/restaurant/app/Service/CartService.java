package restaurant.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.app.Models.Item;
import restaurant.app.Repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private HttpSession session;
    private ProductRepository productRepository;

    @Autowired
    public CartService(ProductRepository productRepository, HttpSession session) {
        this.productRepository = productRepository;
        this.session = session;
    }

    public void addProductToCart(Integer idProduct){
        if(session.getAttribute("cart")==null){
            List<Item> cart = new ArrayList<>();
            cart.add(new Item(productRepository.findByIdProduct(idProduct),1));
            session.setAttribute("cart", cart);
        }else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = alreadyExists(idProduct, cart);
            if(index == -1){
                cart.add(new Item(productRepository.findByIdProduct(idProduct),1));
            }else {
                int quantity = cart.get(index).getQuantity()+1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
    }

    public void removeProductFromCart(Integer idProduct){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = alreadyExists(idProduct, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
    }

    public void updateProductFromCart(HttpServletRequest request){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        String [] quantities = request.getParameterValues("quantity");
        for(int i=0; i<cart.size(); i++){
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart", cart);
    }

    public double getTotal(){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if(cart==null){
            return 0;
        }
        else {
            double tempTotal;
            int tempQuantity;
            double tempPrice;
            double total = 0;
            for (int i = 0; i < cart.size(); i++) {
                tempQuantity = cart.get(i).getQuantity();
                tempPrice = cart.get(i).getProduct().getPrice();
                tempTotal = tempQuantity * tempPrice;
                total = total + tempTotal;
            }
            return total;
        }
    }

    public int alreadyExists(int idProduct, List <Item> cart){
        for (int i=0; i<cart.size(); i++){
            if(cart.get(i).getProduct().getIdProduct()==idProduct){
                return i;
            }
        }
        return -1;
    }
}
