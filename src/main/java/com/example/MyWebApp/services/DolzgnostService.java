package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Dolzgnost;
import com.example.MyWebApp.repo.DolzgnostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DolzgnostService {

    public DolzgnostService() {}

    @Autowired
    DolzgnostRepository dolzgnostRepository;

    public Dolzgnost findById(int id) {
        return dolzgnostRepository.findById(id).orElseThrow();
    }

    public List<Dolzgnost> findAll() {
        return dolzgnostRepository.findAll();
    }

    public void save(Dolzgnost dolzgnost) {
        dolzgnostRepository.save(dolzgnost);
    }
    public void deleteDolzgnost(int id) {
        dolzgnostRepository.deleteById(id);
    }
}
