package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {

}
