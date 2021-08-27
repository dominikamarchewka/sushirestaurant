package restaurant.app.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyAdminDetails implements UserDetails {

    private String nameAdmin;
    private String lastNameAdmin;
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorityList;

    public MyAdminDetails(Admin admin){
        this.nameAdmin=admin.getNameAdmin();
        this.lastNameAdmin=admin.getLastNameAdmin();
        this.username=admin.getUsername();
        this.password=admin.getPassword();
        this.active=admin.isActive();
        this.authorityList=Arrays.stream(admin.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public MyAdminDetails(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
