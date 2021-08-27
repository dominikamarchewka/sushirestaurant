package restaurant.app.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import restaurant.app.Models.Admin;
import restaurant.app.Repository.AdminRepository;

@Controller
public class AdminController {
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/formAdmin")
    public String formAdmin() {
        return "formAdmin";
    }

    @RequestMapping("/addAdmin")
    public String addAdmin(
            @ModelAttribute Admin admin,
            Model model)
            throws Exception {
        admin.setRoles("ROLE_ADMIN");
        admin.setActive(true);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        model.addAttribute("admin", admin);
        return "saveAdmin";
    }

    @RequestMapping("/admin")
    public String adminHome()
            throws Exception {
        return "admin";
    }
}
