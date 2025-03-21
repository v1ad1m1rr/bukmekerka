package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Role;
import com.example.MyWebApp.models.Sotrudnik;
import com.example.MyWebApp.repo.SotrudnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SotrudnikService {
    @Autowired
    SotrudnikRepository sotrudnikRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public SotrudnikService() {}

    public Sotrudnik findById(int id) {
        return sotrudnikRepository.findById(id).orElseThrow();
    }

    public Sotrudnik findByUsername(String username) {
        return sotrudnikRepository.findByEmail(username);
    }

    public List<Sotrudnik> findAll() {
        return sotrudnikRepository.findAll();
    }

    public List<String> clientsAndStavki(int day, int month, int year) {
        return sotrudnikRepository.findClientsAndStavki(day, month, year);
    }

    public List<String> stavkiViplati() {
        return sotrudnikRepository.findStavkiViplati();
    }

    public List<String> countSucAndLos() {
        return sotrudnikRepository.findCountSucAnsLos();
    }

    public List<String> StavVipAtMonth() {
        return sotrudnikRepository.findStavVipAtMonth();
    }

    public List<String> bestKatMatch() {
        return sotrudnikRepository.findBestKatMatch();
    }

    public void saveSotr(Sotrudnik sotrudnik) {
        sotrudnik.setRole(Collections.singleton(new Role(2, "ROLE_ADMIN")));
        sotrudnik.setPassword(passwordEncoder.encode(sotrudnik.getPassword()));
        sotrudnikRepository.save(sotrudnik);
    }
    public void updateSotr(Sotrudnik sotrudnik) {
        sotrudnik.setPassword(passwordEncoder.encode(sotrudnik.getPassword()));
        sotrudnikRepository.save(sotrudnik);
    }
    public void updateSotrWithoutPass(Sotrudnik sotrudnik) {
        sotrudnikRepository.save(sotrudnik);
    }
    public void deleteSotr(int id) {
        if (sotrudnikRepository.findById(id).isPresent()) {
            sotrudnikRepository.deleteById(id);
        }
    }
}
