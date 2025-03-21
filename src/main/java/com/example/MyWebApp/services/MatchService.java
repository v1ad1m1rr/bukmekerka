package com.example.MyWebApp.services;

import com.example.MyWebApp.models.Matches;
import com.example.MyWebApp.repo.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    @Autowired
    MatchRepository matchRepository;

    public MatchService() {}

    public Matches findById(int id) {
        return matchRepository.findById(id).orElseThrow();
    }
    public List<Matches> findAll() {
        return matchRepository.findAll();
    }

    public void save(Matches matches) {
        matchRepository.save(matches);
    }
    public void deleteMatch(int id) {
        matchRepository.deleteById(id);
    }

    public List<String> allMatches() {
        return matchRepository.findAllMatches();
    }

    public List<String> MatchByCategory(int id_category) {
        return matchRepository.findMatchByCategory(id_category);
    }

    public List<String> NameTeamsById(int id_match) {
        return matchRepository.findNameTeams(id_match);
    }

    public List<String> StavkaOnEndMatch(int id_match) {
        return matchRepository.findStavkaOnEndMatch(id_match);
    }
}
