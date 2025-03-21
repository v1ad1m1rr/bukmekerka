package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Viplata;
import com.example.MyWebApp.repo.ViplataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ViplataService {

    public ViplataService() {}

    @Autowired
    ViplataRepository viplataRepository;

    public Viplata findById(int id) {
        return viplataRepository.findById(id).orElseThrow();
    }

    public List<Viplata> findAll() {
        return viplataRepository.findAll();
    }

    public void save(Viplata viplata) {
        viplataRepository.save(viplata);
    }
    public void deleteViplata(int id) {
        viplataRepository.deleteById(id);
    }
}
