package com.example.MyWebApp.configurations;

import com.example.MyWebApp.models.Client;
import com.example.MyWebApp.models.Sotrudnik;
import com.example.MyWebApp.repo.ClientRepository;
import com.example.MyWebApp.repo.SotrudnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SotrudnikRepository sotrudnikRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(username);
        Sotrudnik sotrudnik = sotrudnikRepository.findByEmail(username);
        if (client == null && sotrudnik == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (client == null) {
            return new MySotrDetails(sotrudnik);
        }
        return new MyClientDetails(client);
    }
}
