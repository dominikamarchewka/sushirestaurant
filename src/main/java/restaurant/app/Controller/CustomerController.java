package restaurant.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import restaurant.app.Models.Customer;
import restaurant.app.Models.Item;
import restaurant.app.Models.Orders;
import restaurant.app.Repository.CustomerRepository;
import restaurant.app.Service.CustomerService;
import restaurant.app.Service.EmailSender;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CustomerController {
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private EmailSender emailSender;
    private HttpSession session;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService, EmailSender emailSender, HttpSession httpSession) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.emailSender = emailSender;
        this.session = httpSession;
    }

    @RequestMapping("/form")
    public String form() {
        return "form";
    }

    @RequestMapping("/add")
    public String adding(
            @ModelAttribute Customer customer, Orders orders,
            Model model)
            throws Exception {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        customerService.addCustomer(customer, orders);
        emailSender.send(customer.getMail(), "Dziękujemy za złożenie zamówienia w Beyond Sushi!",
                "Restauracja rozpoczyna przygotowywać zamówienie na ulicę " + customer.getStreet() + " "
                        + customer.getApartment() + ". BeyondSushi.");
        emailSender.send("sushiaplikacja@gmail.com", "Zamówienie",  "add"
                );
        model.addAttribute("customer", customer);
        model.addAttribute("order", orders);
        return "add";
    }

    @RequestMapping("/showAllCustomers")
    public String show(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "allCustomers";
    }

    @RequestMapping("/delete")
    public String deleteById(Integer id, Model model) {
        customerRepository.deleteById(id);
        model.addAttribute("customers", customerRepository.findAll());
        return "allCustomers";
    }

    @RequestMapping("/find")
    public String find(@RequestParam("lastName") String lastName, Model model) {
        model.addAttribute("customers", customerRepository.findAllBylastName(lastName));
        return "allCustomers";
    }

    @RequestMapping("/edit")
    public String showUpdateForm(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id)));
        return "update";
    }

    @RequestMapping(value = "/update")
    public String update(
            @ModelAttribute Customer customer, Model model)
            throws Exception {
        customerRepository.save(customer);
        model.addAttribute("customers", customerRepository.findAll());
        return "allCustomers";
    }
}
