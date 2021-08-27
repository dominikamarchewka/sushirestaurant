package restaurant.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import restaurant.app.Models.Admin;
import restaurant.app.Models.MyAdminDetails;
import restaurant.app.Repository.AdminRepository;

import java.util.Optional;

@Service
public class MyAdminDetailService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(username);

        admin.orElseThrow(()->new UsernameNotFoundException("Not found "+ username));

        return admin.map(MyAdminDetails::new).get();
    }
}
