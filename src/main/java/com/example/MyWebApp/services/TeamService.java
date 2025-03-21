package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Team;
import com.example.MyWebApp.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public TeamService() {}

    public Team findById(int id) {
        return teamRepository.findById(id).orElseThrow();
    }
}
