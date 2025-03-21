package com.example.MyWebApp.configurations;

import com.example.MyWebApp.models.Sotrudnik;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MySotrDetails implements UserDetails {
    private Sotrudnik sotrudnik;

    public MySotrDetails(Sotrudnik sotrudnik) {
        this.sotrudnik = sotrudnik;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sotrudnik.getRole();
    }

    @Override
    public String getPassword() {
        return sotrudnik.getPassword();
    }

    @Override
    public String getUsername() {
        return sotrudnik.getEmail();
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
