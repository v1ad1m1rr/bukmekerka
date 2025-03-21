package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Kat_match;
import com.example.MyWebApp.repo.Kat_matchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Kat_matchService {

    public Kat_matchService() {}

    @Autowired
    Kat_matchRepository kat_matchRepository;

    public Kat_match findById(int id) {
        return kat_matchRepository.findById(id).orElseThrow();
    }

    public List<Kat_match> findAll() {
        return kat_matchRepository.findAll();
    }

    public void save(Kat_match kat_match) {
        kat_matchRepository.save(kat_match);
    }
    public void deleteCategory(int id) {
        kat_matchRepository.deleteById(id);
    }
}
