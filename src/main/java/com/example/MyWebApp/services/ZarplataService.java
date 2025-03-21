package com.example.MyWebApp.services;


import com.example.MyWebApp.models.Zarplata;
import com.example.MyWebApp.repo.ZarplataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZarplataService {

    public ZarplataService() {}

    @Autowired
    ZarplataRepository zarplataRepository;

    public Zarplata findById(int id) {
        return zarplataRepository.findById(id).orElseThrow();
    }

    public List<Zarplata> findAll() {
        return zarplataRepository.findAll();
    }

    public void save(Zarplata zarplata) {
        zarplataRepository.save(zarplata);
    }
    public void deleteZarplata(int id) {
        zarplataRepository.deleteById(id);
    }
}
