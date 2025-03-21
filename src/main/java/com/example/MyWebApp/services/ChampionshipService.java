package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Championship;
import com.example.MyWebApp.repo.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionshipService {
    @Autowired
    ChampionshipRepository championshipRepository;

    public ChampionshipService() {}

    public Championship findById(int id) {
        return championshipRepository.findById(id).orElseThrow();
    }
}
