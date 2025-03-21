package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Client;
import com.example.MyWebApp.models.Role;
import com.example.MyWebApp.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ClientService() {}

    public Client findById(int id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public Client findByUsername(String username) {
        return clientRepository.findByEmail(username);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public List<String> histStavkaAndViplata(int id) {
        return clientRepository.findByCustomQuery(id);
    }

    public List<String> sumStavkaViplata(int id) {
        return clientRepository.findSumStavkaViplata(id);
    }

    public List<String> countAnyStavok(int id) {
        return clientRepository.findCountsAnyStavok(id);
    }

    public List<String> BetsViplataNull(int id) {
        return clientRepository.findBetsViplataNull(id);
    }

    public void saveClient(Client client) {
        client.setRole(Collections.singleton(new Role(1, "ROLE_USER")));
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
    }
    public void updateClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
    }
    public void updateClientWithoutPass(Client client) {
        clientRepository.save(client);
    }
    public void deleteClient(int id) {
        if (clientRepository.findById(id).isPresent()) {
            clientRepository.deleteById(id);
        }
    }
}
