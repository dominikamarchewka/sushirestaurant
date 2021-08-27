package restaurant.app.Service;

import org.springframework.stereotype.Service;
import restaurant.app.Models.Customer;
import restaurant.app.Models.Orders;
import restaurant.app.Repository.CustomerRepository;
import restaurant.app.Repository.OrdersRepository;

import java.util.Date;


@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private OrdersRepository ordersRepository;
    private CartService cartService;

    public CustomerService(CustomerRepository customerRepository, OrdersRepository ordersRepository, CartService cartService) {
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
        this.cartService = cartService;
    }

    public void addCustomer(Customer customer, Orders orders){
        double total = cartService.getTotal();
        Date nowDate = new Date();
        orders.setCustomer(customer);
        orders.setStatus("Zatwierdzono");
        orders.setTotalAmount(total);
        orders.setOrderDate(nowDate);
        if(orders.getPayment().equals("KARTA")){
            orders.setPayment("KARTA");
        } else if (orders.getPayment().equals("GOTÓWKA"))
        {
            orders.setPayment("GOTÓWKA");
        }
        customerRepository.save(customer);
        ordersRepository.save(orders);
    }
}