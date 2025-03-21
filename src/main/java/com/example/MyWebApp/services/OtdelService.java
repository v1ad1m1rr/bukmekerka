package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Otdel;
import com.example.MyWebApp.repo.OtdelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OtdelService {

    public OtdelService() {}

    @Autowired
    OtdelRepository otdelRepository;

    public Otdel findById(int id) {
        return otdelRepository.findById(id).orElseThrow();
    }

    public List<Otdel> findAll() {
        return otdelRepository.findAll();
    }

    public void save(Otdel otdel) {
        otdelRepository.save(otdel);
    }
    public void deleteOtdel(int id) {
        otdelRepository.deleteById(id);
    }
}
