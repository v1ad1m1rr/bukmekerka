package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Stavka;
import com.example.MyWebApp.repo.StavkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StavkaService {

    public StavkaService() {}

    @Autowired
    StavkaRepository stavkaRepository;

    public Stavka findById(int id) {
        return stavkaRepository.findById(id).orElseThrow();
    }

    public List<Stavka> findAll() {
        return stavkaRepository.findAll();
    }

    public void save(Stavka stavka) {
        stavkaRepository.save(stavka);
    }
    public void deleteStavka(int id) {
        stavkaRepository.deleteById(id);
    }
}
