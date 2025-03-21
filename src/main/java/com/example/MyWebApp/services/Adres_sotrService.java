package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Adres_sotr;
import com.example.MyWebApp.repo.Adres_sotrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Adres_sotrService {
    @Autowired
    Adres_sotrRepository adres_sotrRepository;

    public Adres_sotrService() {}

    public Adres_sotr findById(int id) {
        return adres_sotrRepository.findById(id).orElseThrow();
    }

    public List<Adres_sotr> findAll() {
        return adres_sotrRepository.findAll();
    }

    public void save(Adres_sotr adres_sotr) {
        adres_sotrRepository.save(adres_sotr);
    }
    public void deleteAdres(int id) {
        adres_sotrRepository.deleteById(id);
    }

}
